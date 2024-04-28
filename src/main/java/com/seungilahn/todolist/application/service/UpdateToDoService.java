package com.seungilahn.todolist.application.service;

import com.seungilahn.todolist.application.port.in.UpdateToDoCommand;
import com.seungilahn.todolist.application.port.in.UpdateToDoUseCase;
import com.seungilahn.todolist.application.port.out.LoadToDoPort;
import com.seungilahn.todolist.application.port.out.SaveToDoPort;
import com.seungilahn.todolist.common.UseCase;
import com.seungilahn.todolist.domain.ToDo;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
class UpdateToDoService implements UpdateToDoUseCase {

    private final LoadToDoPort loadToDoPort;
    private final SaveToDoPort saveToDoPort;

    UpdateToDoService(LoadToDoPort loadToDoPort, SaveToDoPort saveToDoPort) {
        this.loadToDoPort = loadToDoPort;
        this.saveToDoPort = saveToDoPort;
    }

    @Override
    public ToDo update(UpdateToDoCommand command) {

        ToDo toDo = loadToDoPort.loadById(command.toDoId());

        toDo.changeStatus(command.status());

        return saveToDoPort.save(toDo);
    }

}
