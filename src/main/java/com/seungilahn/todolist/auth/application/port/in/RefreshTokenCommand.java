package com.seungilahn.todolist.auth.application.port.in;

public record RefreshTokenCommand(
        String refreshToken
) {
}
