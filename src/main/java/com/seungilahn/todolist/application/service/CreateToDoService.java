package com.seungilahn.todolist.application.service;

import com.seungilahn.todolist.application.port.in.CreateToDoCommand;
import com.seungilahn.todolist.application.port.in.CreateToDoUseCase;
import com.seungilahn.todolist.application.port.out.SaveToDoPort;
import com.seungilahn.todolist.common.UseCase;
import com.seungilahn.todolist.domain.ToDo;
import com.seungilahn.todolist.domain.ToDoStatus;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
class CreateToDoService implements CreateToDoUseCase {

    private final SaveToDoPort saveToDoPort;

    CreateToDoService(SaveToDoPort saveToDoPort) {
        this.saveToDoPort = saveToDoPort;
    }

    @Override
    public ToDo create(CreateToDoCommand command) {

        ToDo toDo = ToDo.withoutId(command.userId(), command.title(), command.content(), ToDoStatus.TODO);
        return saveToDoPort.save(toDo);
    }

}
