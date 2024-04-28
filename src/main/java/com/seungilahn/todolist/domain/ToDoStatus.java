package com.seungilahn.todolist.domain;

import java.util.Objects;

public enum ToDoStatus {
    TODO("할 일")
    , ONGOING("진행 중")
    , STANDBY("대기")
    , DONE("완료")
    ;

    ToDoStatus(String description) {
    }

    public static ToDoStatus get(String status) {
        Objects.requireNonNull(status, "status must not be null");

        for (ToDoStatus value : values()) {
            if (value.name().equals(status)) {
                return value;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + status + "]");
    }

}
