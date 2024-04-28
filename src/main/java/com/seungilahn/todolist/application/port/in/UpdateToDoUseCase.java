package com.seungilahn.todolist.application.port.in;

import com.seungilahn.todolist.domain.ToDo;

public interface UpdateToDoUseCase {

    ToDo update(UpdateToDoCommand command);

}
