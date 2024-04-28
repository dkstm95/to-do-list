package com.seungilahn.todolist.application.port.in;

import com.seungilahn.todolist.domain.ToDo;

public interface CreateToDoUseCase {

    ToDo create(CreateToDoCommand command);

}
