package com.seungilahn.todolist.application.service;

import com.seungilahn.todolist.application.port.in.UpdateToDoCommand;
import com.seungilahn.todolist.application.port.in.UpdateToDoUseCase;
import com.seungilahn.todolist.application.port.out.LoadToDoPort;
import com.seungilahn.todolist.application.port.out.SaveToDoPort;
import com.seungilahn.todolist.common.UseCase;
import com.seungilahn.todolist.domain.ToDo;
import com.seungilahn.todolist.domain.ToDoStatus;
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

        validateToDoStatus(toDo, command.status());

        toDo.changeStatus(command.status());

        return saveToDoPort.save(toDo);
    }

    private void validateToDoStatus(ToDo toDo, ToDoStatus commandStatus) {
        if (commandStatus == ToDoStatus.STANDBY && !toDo.isOngoing()) {
            throw new IllegalStateException("Cannot change status to STANDBY when current status is not ONGOING");
        }
    }

}
