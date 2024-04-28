package com.seungilahn.todolist.auth.adapter.in.web;

import com.seungilahn.todolist.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.todolist.auth.application.port.in.RefreshTokenUseCase;
import com.seungilahn.todolist.common.ApiResponse;
import com.seungilahn.todolist.common.WebAdapter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
class RefreshTokenController {

    private final RefreshTokenUseCase useCase;

    RefreshTokenController(RefreshTokenUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping("/api/v1/auth/refresh-token")
    ApiResponse<AuthenticationResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return ApiResponse.ok(useCase.tokenRefresh(request.toCommand()));
    }

}
