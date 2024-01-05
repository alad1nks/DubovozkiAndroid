pluginManagement {
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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DubovozkiAndroid"
include(":app")
include(":feature")
include(":core")
include(":core:ui")
include(":core:model")
include(":core:design-system")
include(":core:domain")
include(":core:data")
include(":core:database")
include(":core:network")
include(":core:datastore")
