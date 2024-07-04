import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class PresentationConventionPlugin : AndroidConvention, HiltConvention {
  override fun apply(target: Project) {
    super<AndroidConvention>.apply(target)
    super<HiltConvention>.apply(target)

    with(target) {
      val extension = extensions.getByType<LibraryExtension>()

      extension.viewBinding.enable = true

      extensions.getByType<KotlinAndroidProjectExtension>().apply {
        compilerOptions.freeCompilerArgs.addAll(
          "-opt-in=kotlinx.coroutines.ObsoleteCoroutinesApi",
        )
      }

      val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

      dependencies {
        add("implementation", project(":data-api"))
        add("runtimeOnly", project(":data-impl"))

        add("implementation", libs.findBundle("lifecycle").get())
        add("implementation", libs.findLibrary("material").get())
        add("implementation", libs.findLibrary("activity").get())
        add("implementation", libs.findLibrary("fragment").get())
      }
    }
  }
}