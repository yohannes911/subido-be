import Config.springBootVersion

dependencyManagement {
    dependencies {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
        }
    }
}

dependencies {
    implementation(Config.Libs.spring_boot_starter_data_jpa)
    implementation(Config.Libs.findbugs_annotations)
    implementation(Config.Libs.spring_boot_starter_validation)
    implementation(Config.Libs.commons_lang3)
    implementation(Config.Libs.swagger_annotations)
    compileOnly(Config.Libs.jsr305)
    compileOnly(Config.Libs.lombok)
    annotationProcessor(Config.Libs.lombok)

    testImplementation(Config.TestLibs.spring_boot_starter_test)
}

tasks.test {
    useJUnitPlatform()
}
