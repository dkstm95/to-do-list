package com.seungilahn.todolist.application.port.out;

import com.seungilahn.todolist.domain.ToDo;

public interface SaveToDoPort {

    ToDo save(ToDo toDo);

}
