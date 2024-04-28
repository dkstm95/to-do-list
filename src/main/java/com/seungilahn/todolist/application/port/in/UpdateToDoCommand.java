package com.seungilahn.todolist.application.port.in;

import com.seungilahn.todolist.domain.ToDoStatus;

public record UpdateToDoCommand(
        Long toDoId,
        ToDoStatus status
) {
}
