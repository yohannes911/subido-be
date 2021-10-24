plugins {
    id("org.openapi.generator") version Config.openapiGeneratorPluginVersion
}

dependencyManagement {
    dependencies {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:${Config.springBootVersion}")
        }
    }
}

dependencies {
    implementation(project(":subido-model"))
    compileOnly(Config.Libs.jsr305)
    implementation(Config.Libs.findbugs_annotations)
    implementation(Config.Libs.spring_boot_configuration_processor)
    implementation(Config.Libs.spring_boot_starter_validation)
    implementation(Config.Libs.spring_boot_starter_webflux)
    implementation(Config.Libs.spring_boot_starter_data_jpa)
    implementation(Config.Libs.commons_lang3)
    implementation(Config.Libs.postgresql)
    implementation(Config.Libs.springdoc_openapi_webflux_ui) {
        exclude(group = "javax.xml.bind", module = "jaxb-api")
        exclude(group = "javax.validation", module = "validation-api")
    }
    compileOnly(Config.Libs.lombok)
    annotationProcessor(Config.Libs.lombok)

    testImplementation(Config.TestLibs.spring_boot_starter_test)
}

openApiGenerate {
    generatorName.set("typescript-angular")
    templateDir.set("$projectDir/src/main/template")
    inputSpec.set("$buildDir/resources/main/subido-api.json")
    outputDir.set("$buildDir/typescript-angular-generated-client")
    modelPackage.set("model")
    apiPackage.set("api")
    typeMappings.set(mapOf("set" to "Array"))
    globalProperties.set(mapOf("skipFormModel" to "false"))
    configOptions.set(
        mapOf(
            "withInterfaces" to "false",
            "supportES6" to "true",
            "ngVersion" to "12.2",
            "npmName" to "${project.name}-client",
            "npmVersion" to "1.0.${project.version}",
            "stringEnums" to "true",
            "enumPropertyNaming" to "original",
            "enumNameSuffix" to "",
            "serviceSuffix" to "Service"
        )
    )
}

tasks.named("openApiGenerate").configure {
    dependsOn(tasks.named("test"))
}

tasks.named("build").configure {
    dependsOn(tasks.named("openApiGenerate"))
}
