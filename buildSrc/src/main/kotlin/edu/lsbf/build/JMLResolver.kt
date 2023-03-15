package edu.lsbf.build
import org.gradle.api.services.BuildServiceParameters
import org.gradle.jvm.toolchain.JavaToolchainDownload
import org.gradle.jvm.toolchain.JavaToolchainRequest
import org.gradle.jvm.toolchain.JavaToolchainResolver
import java.util.*

open class JMLResolver : JavaToolchainResolver {
  override fun getParameters(): BuildServiceParameters.None {
    TODO("Not yet implemented")
  }

  override fun resolve(request: JavaToolchainRequest): Optional<JavaToolchainDownload> {
    TODO("Not yet implemented")
  }
}