package com.seungilahn.todolist.adapter.in.web;

import com.seungilahn.todolist.application.port.in.CreateToDoUseCase;
import com.seungilahn.todolist.common.ApiResponse;
import com.seungilahn.todolist.common.LoginUser;
import com.seungilahn.todolist.common.WebAdapter;
import com.seungilahn.todolist.domain.ToDo;
import com.seungilahn.todolist.user.domain.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
class CreateToDoController {

    private final CreateToDoUseCase useCase;

    CreateToDoController(CreateToDoUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/api/v1/todos")
    ApiResponse<ToDo> create(@LoginUser User user,
                             @RequestBody @Valid CreateToDoRequest request) {
        return ApiResponse.ok(useCase.create(request.toCommand(user.getId())));
    }

}
