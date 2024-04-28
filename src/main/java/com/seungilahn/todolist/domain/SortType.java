package com.seungilahn.todolist.domain;

import java.util.Objects;

public enum SortType {
    ASC, DESC;

    public static SortType get(String sortType) {
        Objects.requireNonNull(sortType, "sortType must not be null");

        for (SortType value : values()) {
            if (value.name().equalsIgnoreCase(sortType)) {
                return value;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + sortType + "]");
    }

}
