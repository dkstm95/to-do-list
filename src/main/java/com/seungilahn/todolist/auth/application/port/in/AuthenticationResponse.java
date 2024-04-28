package com.seungilahn.todolist.auth.application.port.in;

public record AuthenticationResponse(
        String accessToken,
        String refreshToken
) {
}
