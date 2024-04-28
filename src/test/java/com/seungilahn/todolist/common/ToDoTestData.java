package com.seungilahn.todolist.common;

import com.seungilahn.todolist.domain.ToDo;
import com.seungilahn.todolist.domain.ToDoStatus;

public class ToDoTestData {

    public static ToDoTestDataBuilder defaultToDo() {
        return new ToDoTestDataBuilder()
                .withId(1L)
                .withUserId(1L)
                .withTitle("title")
                .withContent("content")
                .withStatus(ToDoStatus.TODO);
    }

    public static class ToDoTestDataBuilder {
        private Long id;
        private Long userId;
        private String title;
        private String content;
        private ToDoStatus status;

        public ToDoTestDataBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ToDoTestDataBuilder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public ToDoTestDataBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ToDoTestDataBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public ToDoTestDataBuilder withStatus(ToDoStatus status) {
            this.status = status;
            return this;
        }

        public ToDo build() {
            return ToDo.withId(id, userId, title, content, status);
        }
    }

}
