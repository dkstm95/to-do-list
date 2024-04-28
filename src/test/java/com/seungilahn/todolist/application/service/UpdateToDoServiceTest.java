package com.seungilahn.todolist.application.service;

import com.seungilahn.todolist.application.port.in.UpdateToDoCommand;
import com.seungilahn.todolist.application.port.out.LoadToDoPort;
import com.seungilahn.todolist.application.port.out.SaveToDoPort;
import com.seungilahn.todolist.common.ToDoTestData;
import com.seungilahn.todolist.domain.ToDo;
import com.seungilahn.todolist.domain.ToDoStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

class UpdateToDoServiceTest {

    private final LoadToDoPort loadToDoPort =
            Mockito.mock(LoadToDoPort.class);

    private final SaveToDoPort saveToDoPort =
            Mockito.mock(SaveToDoPort.class);

    private final UpdateToDoService updateToDoService =
            new UpdateToDoService(loadToDoPort, saveToDoPort);

    @Test
    void throw_exception_when_command_status_is_standby_and_todo_is_not_ongoing() {
        // given
        ToDo toDo = ToDoTestData.defaultToDo()
                .withId(1L)
                .withStatus(ToDoStatus.TODO)
                .build();
        given(loadToDoPort.loadById(1L))
                .willReturn(toDo);

        UpdateToDoCommand command = new UpdateToDoCommand(1L, ToDoStatus.STANDBY);

        // when
        // then
        assertThatThrownBy(() -> updateToDoService.update(command))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot change status to STANDBY when current status is not ONGOING");
    }

}