package com.seungilahn.todolist.auth.adapter.in.web;

import com.seungilahn.todolist.auth.application.port.in.SignoutUseCase;
import com.seungilahn.todolist.common.ApiResponse;
import com.seungilahn.todolist.common.WebAdapter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
class SignoutController {

    private final SignoutUseCase useCase;

    SignoutController(SignoutUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/api/v1/auth/signout")
    ApiResponse<Void> signout(@RequestBody @Valid SignoutRequest request) {
        useCase.signout(request.toCommand());
        return ApiResponse.noContent();
    }

}
