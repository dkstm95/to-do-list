package com.seungilahn.todolist.auth.domain;

public record AuthenticationTokens(
        String accessToken,
        String refreshToken
) {
}
