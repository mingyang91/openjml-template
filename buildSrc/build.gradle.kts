plugins {
  `kotlin-dsl`
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(gradleApi())
  implementation("org.apache.commons:commons-compress:1.22")
}

group = "edu.lsbf"
version = "1.0"
