package com.seungilahn.todolist.application.port.in;

import com.seungilahn.todolist.domain.SortType;

public interface GetToDoListQuery {

    GetToDoListResponse getToDoList(Long userId, Long cursor, int limit, SortType sortType);

}
