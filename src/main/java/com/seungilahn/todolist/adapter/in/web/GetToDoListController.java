package com.seungilahn.todolist.adapter.in.web;

import com.seungilahn.todolist.application.port.in.GetToDoListQuery;
import com.seungilahn.todolist.application.port.in.GetToDoListResponse;
import com.seungilahn.todolist.common.ApiResponse;
import com.seungilahn.todolist.common.LoginUser;
import com.seungilahn.todolist.common.WebAdapter;
import com.seungilahn.todolist.domain.SortType;
import com.seungilahn.todolist.domain.ToDo;
import com.seungilahn.todolist.user.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
class GetToDoListController {

    private final GetToDoListQuery query;

    GetToDoListController(GetToDoListQuery query) {
        this.query = query;
    }

    @GetMapping("/api/v1/todos")
    ApiResponse<GetToDoListResponse> update(@LoginUser User user,
                                            @RequestParam(value = "cursor", required = false) Long cursor,
                                            @RequestParam(value = "limit", defaultValue = "10") int limit,
                                            @RequestParam(value = "sort", defaultValue = "DESC") String sort) {
        SortType sortType = SortType.get(sort.toUpperCase());
        return ApiResponse.ok(query.getToDoList(user.getId(), cursor, limit, sortType));
    }

}
