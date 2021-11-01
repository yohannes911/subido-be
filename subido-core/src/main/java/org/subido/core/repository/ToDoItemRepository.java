package org.subido.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.subido.model.entity.TodoItem;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long>, JpaSpecificationExecutor<TodoItem> {
}
