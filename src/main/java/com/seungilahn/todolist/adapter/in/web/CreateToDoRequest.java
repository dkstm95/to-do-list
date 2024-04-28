package com.seungilahn.todolist.adapter.in.web;

import com.seungilahn.todolist.application.port.in.CreateToDoCommand;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

record CreateToDoRequest(
        @NotEmpty @Size(min = 1, max = 30, message = "Title must be between 1 and 30 characters") String title,
        @NotEmpty @Size(min = 1, max = 100, message = "Content must be between 1 and 100 characters") String content
) {

    CreateToDoCommand toCommand(Long userId) {
        return new CreateToDoCommand(userId, title, content);
    }

}
