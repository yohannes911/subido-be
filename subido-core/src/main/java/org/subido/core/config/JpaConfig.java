package org.subido.core.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.subido.core.repository.ToDoItemRepository;
import org.subido.model.entity.ToDoItem;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EntityScan(basePackageClasses = ToDoItem.class)
@EnableJpaRepositories(basePackageClasses = ToDoItemRepository.class)
public class JpaConfig {
}
