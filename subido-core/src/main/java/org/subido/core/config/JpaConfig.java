package org.subido.core.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.subido.core.repository.TodoItemRepository;
import org.subido.model.entity.TodoItem;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EntityScan(basePackageClasses = TodoItem.class)
@EnableJpaRepositories(basePackageClasses = TodoItemRepository.class)
public class JpaConfig {
}
