plugins {
    java
    id("org.springframework.boot") version Config.springBootVersion apply false
    id("com.github.johnrengelman.processes") version Config.processesPluginVersion
    id("io.spring.dependency-management") version Config.springDependencyManagementPluginVersion
}

val javaVersion: JavaVersion by extra { JavaVersion.VERSION_11 }

allprojects {
    group = "org.subido"
    version = "0.0.1-SNAPSHOT"
    configurations.all {
        exclude(module = "junit-vintage-engine")
    }
}

subprojects {
    apply {
        plugin("java")
        plugin("io.spring.dependency-management")
    }

    dependencyManagement {
        dependencies {
            resolutionStrategy {
                cacheChangingModulesFor(0, TimeUnit.SECONDS)
            }
        }
    }

    repositories {
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
    }

    tasks {
        withType<JavaCompile> {
            options.isIncremental = true
            options.encoding = "UTF-8"
            options.compilerArgs.addAll(
                arrayOf(
                    "-parameters",
                    "-Xlint:serial",
                    "-Xlint:varargs",
                    "-Xlint:cast",
                    "-Xlint:classfile",
                    "-Xlint:dep-ann",
                    "-Xlint:divzero",
                    "-Xlint:empty",
                    "-Xlint:finally",
                    "-Xlint:overrides",
                    "-Xlint:path",
                    "-Xlint:-processing",
                    "-Xlint:static",
                    "-Xlint:try",
                    "-Xlint:fallthrough",
                    "-Xlint:rawtypes",
                    "-Xlint:deprecation",
                    "-Xlint:unchecked",
                    "-Xlint:options"
                    // "-Werror"
                )
            )
        }
        withType<Test> {
            useJUnitPlatform {
                excludeTags = setOf("integration-test")
                exclude("**/*ManualTest.class")
            }
        }
        withType<ProcessResources>().named("processResources") {
            filesMatching("application.yml") {
                expand(project.properties)
            }
        }
    }
}
