package com.seungilahn.todolist.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private ToDoStatus status;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    protected ToDo() {
    }

    private ToDo(Long id, Long userId, String title, String content, ToDoStatus status) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    /**
     * Creates an {@link ToDo} entity without an ID. Use to create a new entity that is not yet persisted.
     */
    public static ToDo withoutId(Long userId, String title, String content, ToDoStatus status) {
        return new ToDo(null, userId, title, content, status);
    }

    /**
     * Creates an {@link ToDo} entity with an ID. Use to reconstitute a persisted entity.
     */
    public static ToDo withId(Long id, Long userId, String title, String content, ToDoStatus status) {
        return new ToDo(id, userId, title, content, status);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public ToDoStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public boolean isOngoing() {
        return status == ToDoStatus.ONGOING;
    }

    public void changeStatus(ToDoStatus status) {
        this.status = status;
    }

}
