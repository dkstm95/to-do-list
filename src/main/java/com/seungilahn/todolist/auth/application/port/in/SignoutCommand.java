package com.seungilahn.todolist.auth.application.port.in;

public record SignoutCommand(
        String refreshToken
) {

    public SignoutCommand {
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new IllegalArgumentException("refreshToken must not be null or blank");
        }
    }

}
