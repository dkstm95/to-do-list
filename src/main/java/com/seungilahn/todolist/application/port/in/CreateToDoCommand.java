package com.seungilahn.todolist.application.port.in;

public record CreateToDoCommand(
        Long userId,
        String title,
        String content
) {
}
