package com.seungilahn.todolist.adapter.in.web;

import com.seungilahn.todolist.application.port.in.UpdateToDoCommand;
import com.seungilahn.todolist.domain.ToDoStatus;
import jakarta.validation.constraints.NotEmpty;

record UpdateToDoRequest(
        @NotEmpty String status
) {
    UpdateToDoCommand toCommand(Long toDoId) {
        return new UpdateToDoCommand(toDoId, ToDoStatus.get(status.toUpperCase()));
    }
}
