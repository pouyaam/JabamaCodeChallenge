pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

include(":app")
rootProject.name = "Github"
include(":feature:login")
include(":feature:search")
include(":common")
include(":feature:login:presentation")
include(":feature:login:domain")
include(":feature:login:data")
include(":feature:search:presentation")
include(":feature:search:domain")
include(":feature:search:data")
include(":token")
include(":design")
