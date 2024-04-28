package com.seungilahn.todolist.auth.adapter.in.web;

import com.seungilahn.todolist.auth.application.port.in.RefreshTokenCommand;
import jakarta.validation.constraints.NotEmpty;

record RefreshTokenRequest(
        @NotEmpty String refreshToken
) {

    RefreshTokenCommand toCommand() {
        return new RefreshTokenCommand(refreshToken);
    }

}
