package com.seungilahn.todolist.auth.adapter.in.web;

import com.seungilahn.todolist.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.todolist.auth.application.port.in.SignupUseCase;
import com.seungilahn.todolist.common.ApiResponse;
import com.seungilahn.todolist.common.WebAdapter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
class SignupController {

    private final SignupUseCase useCase;

    SignupController(SignupUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/api/v1/auth/signup")
    ApiResponse<AuthenticationResponse> signup(@RequestBody @Valid SignupRequest request) {
        return ApiResponse.ok(useCase.signup(request.toCommand()));
    }

}
