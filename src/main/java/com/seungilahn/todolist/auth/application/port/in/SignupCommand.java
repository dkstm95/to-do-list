package com.seungilahn.todolist.auth.application.port.in;

import com.seungilahn.todolist.user.domain.Role;

public record SignupCommand(
        String email,
        String password,
        String nickname,
        Role role
) {
}
