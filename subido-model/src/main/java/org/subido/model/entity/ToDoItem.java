package org.subido.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.subido.model.Priority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "todo_item")
public class ToDoItem {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Length(min = 1, max = 128)
    private String name;

    @Getter
    @Setter
    private ZonedDateTime deadline;

    @Getter
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToDoItem)) {
            return false;
        }
        ToDoItem toDoItem = (ToDoItem) o;
        return Objects.equals(id, toDoItem.id) &&
                name.equals(toDoItem.name) &&
                deadline.equals(toDoItem.deadline) &&
                Objects.equals(createdAt, toDoItem.createdAt) &&
                priority == toDoItem.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deadline, createdAt, priority);
    }
}
