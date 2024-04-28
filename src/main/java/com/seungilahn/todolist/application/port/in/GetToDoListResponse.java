package com.seungilahn.todolist.application.port.in;

import com.seungilahn.todolist.domain.ToDo;

import java.util.List;

public record GetToDoListResponse(
        Long totalCount,
        String nextCursor,
        List<ToDo> data
) {
}
