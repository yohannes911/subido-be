pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "subido-backend"

include(
    "subido-model",
    "subido-core",
    "subido-app"
)