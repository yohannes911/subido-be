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
    implementation(Config.Libs.spring_boot_starter_webflux) {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-reactor-netty")
    }
    implementation(Config.Libs.spring_boot_starter_undertow)
    // implementation(Config.Libs.spring_boot_starter_reactor_netty)
    // implementation(Config.Libs.spring_boot_starter_tomcat)
    implementation(Config.Libs.spring_boot_starter_data_jpa)
    implementation(Config.Libs.commons_lang3)
    implementation(Config.Libs.postgresql)
    implementation(Config.Libs.springdoc_openapi_webflux_ui)
    compileOnly(Config.Libs.lombok)
    annotationProcessor(Config.Libs.lombok)

    testImplementation(Config.TestLibs.spring_boot_starter_test)
}

openApiGenerate {
    generatorName.set("typescript-angular")
    templateDir.set("$projectDir/src/main/template")
    inputSpec.set("$buildDir/resources/main/subido-api.json")
    outputDir.set("$projectDir/../../subido-fe-client")
    modelPackage.set("model")
    apiPackage.set("api")
    typeMappings.set(mapOf("set" to "Array"))
    globalProperties.set(mapOf("skipFormModel" to "false"))
    typeMappings.set(mapOf("DateTime" to "Date"))
    configOptions.set(
        mapOf(
            "supportES6" to "true",
            "ngVersion" to "12.2.0",
            "npmName" to "@subido/subido-fe-client",
            "npmVersion" to "${project.version}",
            "stringEnums" to "true",
            "enumPropertyNaming" to "original",
            "enumNameSuffix" to "",
            "serviceSuffix" to "BEService"
        )
    )
}

tasks.named("openApiGenerate").configure {
    dependsOn(tasks.named("test"))
}

tasks.named("build").configure {
    dependsOn(tasks.named("openApiGenerate"))
}
