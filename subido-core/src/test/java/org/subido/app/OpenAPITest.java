package org.subido.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.subido.core.Core;
import org.subido.core.repository.ToDoItemRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, R2dbcAutoConfiguration.class})
@ComponentScan(basePackageClasses = Core.class)
@SpringBootTest(classes = OpenAPITest.class)
@AutoConfigureWebTestClient
class OpenAPITest {

    @MockBean
    ToDoItemRepository toDoItemRepository;

    @Test
    void openApiTest(@Autowired WebTestClient webClient) throws Exception {
        Path openapiJsonPath = Path.of("build/resources/main/subido-api.json");
        Files.writeString(
                openapiJsonPath,
                Optional.ofNullable(webClient.get().uri("/v3/api-docs")
                                .exchange()
                                .expectStatus()
                                .isOk()
                                .expectBody(String.class)
                                .returnResult()
                                .getResponseBody())
                        .orElseThrow());
        assertTrue(openapiJsonPath.toFile().exists());
    }
}
