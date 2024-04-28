package com.seungilahn.todolist.auth.adapter.in.web;

import com.seungilahn.todolist.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.todolist.auth.application.port.in.SigninUseCase;
import com.seungilahn.todolist.common.ApiResponse;
import com.seungilahn.todolist.common.WebAdapter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
class SigninController {

    private final SigninUseCase useCase;

    SigninController(SigninUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/api/v1/auth/signin")
    ApiResponse<AuthenticationResponse> signin(@RequestBody @Valid SigninRequest request) {
        return ApiResponse.ok(useCase.signin(request.toCommand()));
    }

}
