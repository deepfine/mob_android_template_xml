@file:Suppress("UnstableApiUsage")

pluginManagement {
  includeBuild("build-logic")

  repositories {
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven(url = "https://www.jitpack.io")
  }
}

include(
  ":app",
  ":buildconfig",
  ":buildconfig-stub",
  ":data-api",
  ":data-api:network-api",
  ":data-impl",
  ":data-impl:network-impl",
  ":presentation",
  ":presentation:main"
)

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))
