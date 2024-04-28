package com.seungilahn.todolist.domain;

import com.seungilahn.todolist.common.ToDoTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ToDoTest {

    @Test
    void can_be_standby_when_status_is_ongoing_or_standby() {
        // given
        ToDo sut1 = ToDoTestData.defaultToDo()
                .withId(1L)
                .withStatus(ToDoStatus.ONGOING)
                .build();
        ToDo sut2 = ToDoTestData.defaultToDo()
                .withId(1L)
                .withStatus(ToDoStatus.STANDBY)
                .build();

        // when
        sut1.changeStatus(ToDoStatus.STANDBY);
        sut2.changeStatus(ToDoStatus.STANDBY);

        // then
        assertThat(sut1.getStatus()).isEqualTo(ToDoStatus.STANDBY);
        assertThat(sut2.getStatus()).isEqualTo(ToDoStatus.STANDBY);
    }

    @Test
    void cannot_be_standby_when_status_is_todo_or_done() {
        // given
        ToDo sut1 = ToDoTestData.defaultToDo()
                .withId(1L)
                .withStatus(ToDoStatus.TODO)
                .build();
        ToDo sut2 = ToDoTestData.defaultToDo()
                .withId(1L)
                .withStatus(ToDoStatus.DONE)
                .build();

        // when
        // then
        assertThatThrownBy(() -> sut1.changeStatus(ToDoStatus.STANDBY))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot change status to STANDBY when current status is not ONGOING");
        assertThatThrownBy(() -> sut2.changeStatus(ToDoStatus.STANDBY))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot change status to STANDBY when current status is not ONGOING");
    }

}