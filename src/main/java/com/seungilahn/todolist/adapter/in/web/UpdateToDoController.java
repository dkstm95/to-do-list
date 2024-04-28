package com.seungilahn.todolist.adapter.in.web;

import com.seungilahn.todolist.application.port.in.UpdateToDoUseCase;
import com.seungilahn.todolist.common.ApiResponse;
import com.seungilahn.todolist.common.LoginUser;
import com.seungilahn.todolist.common.WebAdapter;
import com.seungilahn.todolist.domain.ToDo;
import com.seungilahn.todolist.user.domain.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
class UpdateToDoController {

    private final UpdateToDoUseCase useCase;

    UpdateToDoController(UpdateToDoUseCase useCase) {
        this.useCase = useCase;
    }

    @PutMapping("/api/v1/todos/{id}")
    ApiResponse<ToDo> update(@LoginUser User user,
                             @PathVariable Long id,
                             @RequestBody @Valid UpdateToDoRequest request) {
        return ApiResponse.ok(useCase.update(request.toCommand(id)));
    }

}
