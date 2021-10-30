pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "subido-be"

include(
    "subido-model",
    "subido-core",
    "subido-app"
)
