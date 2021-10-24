package org.subido.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.subido.core.Core;

@SpringBootApplication
@ComponentScan(basePackageClasses = Core.class)
public class SubidoApp {

    public static void main(String[] args) {
        SpringApplication.run(SubidoApp.class, args);
    }
}
