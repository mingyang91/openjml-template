import org.apache.commons.compress.archivers.zip.ZipArchiveEntry
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream
import org.apache.commons.compress.archivers.zip.ZipFile
import org.gradle.internal.os.OperatingSystem;
import java.net.URL
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.nio.file.Files


plugins {
    id("java")
}

group = "edu.lsbf"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(files("libs/jmlruntime.jar"))

    testImplementation("junit:junit:4.13.2")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.9.2")
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

val openjml_path = layout.projectDirectory.dir("openjml")

tasks.test {
    java {
        executable = "$openjml_path/jdk/bin/java"
        jvmArgs = listOf("-Dorg.jmlspecs.openjml.rac=exception")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

tasks.withType<JavaCompile>().configureEach {
    if (name == "compileJava") {
        options.isFork = true
        options.forkOptions.javaHome = File("$openjml_path/jdk")
    }
}

tasks.register("downloadOpenJML", ::downloadOpenJML)
tasks.register("esc", JavaCompile::class.java) {

}
tasks.register("rac", JavaCompile::class.java) {}

fun downloadOpenJML(action: Task) {
    val currentOS = OperatingSystem.current()
    val jmlDownloadAddress = if (currentOS.isMacOsX) {
        "https://github.com/OpenJML/OpenJML/releases/download/0.17.0-alpha-15/openjml-macos-11-0.17.0-alpha-15.zip"
    } else if (currentOS.isLinux) {
        "https://github.com/OpenJML/OpenJML/releases/download/0.17.0-alpha-15/openjml-ubuntu-20.04-0.17.0-alpha-15.zip"
    } else {
        throw UnsupportedOperationException("Current OS is not supported by OpenJML")
    }

    val downloadFile = layout.buildDirectory
        .file("tmp/download/openjml.zip").get()

    val unzipFile = openjml_path

    if (downloadFile.asFile.exists()) {
        logger.lifecycle("👌 OpenJML package is present in ${downloadFile}, no need to do this")
    } else {
        downloadFile.asFile.parentFile.mkdirs()
        downloadFile.asFile.createNewFile()

        val url = URL(jmlDownloadAddress)
        Channels.newChannel(url.openStream()).use { inChan ->
            logger.lifecycle("⬇️ OpenJML downloading")
            downloadFile.asFile.outputStream().channel.use { outChan ->
                outChan.transferFrom(inChan, 0, Long.MAX_VALUE);
            }
        }
        logger.lifecycle("✅ OpenJML downloaded, $downloadFile")
    }


    if (unzipFile.asFile.exists()) {
        logger.lifecycle("👌 OpenJML home is present in ${unzipFile}, no need to do this")
    } else {
        logger.lifecycle("\uD83D\uDCE6 OpenJML unpacking, $unzipFile")

        unzipFile.asFile.mkdirs()

        ZipFile(downloadFile.asFile).use { zf ->
            ZipArchiveInputStream(downloadFile.asFile.inputStream()).use { zis ->
                var entry: ZipArchiveEntry? = zis.nextZipEntry
                while (entry != null) {
                    entry.name
                    if (entry.isDirectory) {
                        unzipFile.dir(entry.name).asFile.mkdirs()
                    } else {
                        val newFile = unzipFile.file(entry.name).asFile
                        Channels.newChannel(newFile.outputStream())
                            .use { outChan ->
                                val mode = zf.getEntry(entry!!.name).unixMode
                                var remain = entry!!.size
                                while (remain >= 4096) {
                                    val bytes = zis.readNBytes(4096)
                                    outChan.write(ByteBuffer.wrap(bytes))
                                    remain -= 4096
                                }
                                val tail = zis.readNBytes(remain.toInt())
                                outChan.write(ByteBuffer.wrap(tail))

                                newFile.setReadable(mode.and("0400".toInt(8)) != 0, true)
                                newFile.setWritable(mode.and("0200".toInt(8)) != 0, true)
                                newFile.setExecutable(mode.and("0100".toInt(8)) != 0, true)
                            }
                    }
                    entry = zis.nextZipEntry
                }
            }
        }
        logger.lifecycle("✅ OpenJML unpacked, $unzipFile")
    }

    val originJavac = openjml_path.file("jdk/bin/origin-javac")

    if (originJavac.asFile.exists()) {
        logger.lifecycle("\uD83D\uDC4C javac has been replaced")
    } else {
        val oldJavac = openjml_path.file("jdk/bin/javac")
        oldJavac.asFile.renameTo(originJavac.asFile)
        Files.writeString(oldJavac.asFile.toPath(), """
            #!/bin/bash
            SCRIPT_DIR=${'$'}( cd -- "${'$'}( dirname -- "${'$'}{BASH_SOURCE[0]}" )" &> /dev/null && pwd )
            OPENJML_ROOT="$openjml_path" ${'$'}SCRIPT_DIR/origin-javac -jml --rac ${'$'}@
        """.trimIndent())
        oldJavac.asFile.setExecutable(true, true)
        logger.lifecycle("✅ Javac is replaced, $unzipFile")
    }

    val originJava = openjml_path.file("jdk/bin/origin-java")

    if (originJava.asFile.exists()) {
        logger.lifecycle("\uD83D\uDC4C java has been replaced")
    } else {
        val oldJava = openjml_path.file("jdk/bin/java")
        oldJava.asFile.renameTo(originJava.asFile)
        Files.writeString(oldJava.asFile.toPath(), """
            #!/bin/bash
            SCRIPT_DIR=${'$'}( cd -- "${'$'}( dirname -- "${'$'}{BASH_SOURCE[0]}" )" &> /dev/null && pwd )
            OPENJML_ROOT="$openjml_path" ${'$'}SCRIPT_DIR/origin-java ${'$'}@
        """.trimIndent())
        oldJava.asFile.setExecutable(true, true)
        logger.lifecycle("✅ Java is replaced, $unzipFile")
    }
}