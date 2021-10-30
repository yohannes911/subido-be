object Config {
    const val findbugsAnnotationsVersion = "3.0.1u2"
    const val jsr305Version = "3.0.2"
    const val lombokVersion = "1.18.22"
    const val openapiWebfluxVersion = "1.5.12"
    const val postgresqlVersion = "42.3.0"
    const val r2dbcPostgresqlVersion = "0.8.10.RELEASE"
    const val slf4jVersion = "1.7.30"
    const val springBootVersion = "2.5.6"
    const val springDataJpaVersion = "2.5.6"
    const val springDataR2dbcVersion = "2.5.6"
    const val swaggerVersion = "2.1.11"

    const val gradleGitPropertiesPluginVersion = "2.3.1"
    const val openapiGeneratorPluginVersion = "5.2.1"
    const val processesPluginVersion = "0.5.0"
    const val springDependencyManagementPluginVersion = "1.0.11.RELEASE"

    object Libs {
        const val spring_boot_starter_actuator = "org.springframework.boot:spring-boot-starter-actuator"
        const val spring_boot_starter_data_jpa = "org.springframework.boot:spring-boot-starter-data-jpa:$springDataJpaVersion"
        const val spring_boot_starter_data_r2dbc = "org.springframework.boot:spring-boot-starter-data-r2dbc:$springDataR2dbcVersion"
        const val spring_boot_starter_json = "org.springframework.boot:spring-boot-starter-json"
        const val spring_boot_starter_logging = "org.springframework.boot:spring-boot-starter-logging"
        const val spring_boot_starter_reactor_netty = "org.springframework.boot:spring-boot-starter-reactor-netty"
        const val spring_boot_starter_tomcat = "org.springframework.boot:spring-boot-starter-tomcat"
        const val spring_boot_starter_undertow = "org.springframework.boot:spring-boot-starter-undertow"
        const val spring_boot_starter_validation = "org.springframework.boot:spring-boot-starter-validation"
        const val spring_boot_starter_webflux = "org.springframework.boot:spring-boot-starter-webflux"

        const val springdoc_openapi_webflux_ui = "org.springdoc:springdoc-openapi-webflux-ui:$openapiWebfluxVersion"
        const val swagger_annotations = "io.swagger.core.v3:swagger-annotations:$swaggerVersion"

        const val spring_boot_configuration_processor = "org.springframework.boot:spring-boot-configuration-processor"

        const val reactor_core = "io.projectreactor:reactor-core"
        const val spring_expression = "org.springframework:spring-expression"
        const val spring_webflux = "org.springframework:spring-webflux"

        const val findbugs_annotations = "com.google.code.findbugs:annotations:$findbugsAnnotationsVersion"
        const val jsr305 = "com.google.code.findbugs:jsr305:$jsr305Version"
        const val lombok = "org.projectlombok:lombok:$lombokVersion"
        const val slf4j = "org.slf4j:slf4j-api:$slf4jVersion"

        const val commons_lang3 = "org.apache.commons:commons-lang3"

        const val postgresql = "org.postgresql:postgresql:$postgresqlVersion"
        const val r2dbc_postgresql = "io.r2dbc:r2dbc-postgresql:$r2dbcPostgresqlVersion"
    }

    object TestLibs {
        const val spring_boot_starter_test = "org.springframework.boot:spring-boot-starter-test"
        const val reactor_test = "io.projectreactor:reactor-test"
    }
}
