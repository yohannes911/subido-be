import Config.springBootVersion

dependencyManagement {
    dependencies {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
        }
    }
}

plugins {
    id("org.springframework.boot")
    id("com.gorylenko.gradle-git-properties") version Config.gradleGitPropertiesPluginVersion
}

dependencies {
    implementation(project(":subido-core"))
    implementation(Config.Libs.spring_boot_starter_actuator)

    testImplementation(Config.TestLibs.spring_boot_starter_test)
}
springBoot {
    buildInfo()
}
