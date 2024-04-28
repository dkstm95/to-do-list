package com.seungilahn.todolist.auth.adapter.in.web;

import com.seungilahn.todolist.auth.application.port.in.SignupCommand;
import com.seungilahn.todolist.user.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

record SignupRequest(
        @NotEmpty @Email String email,
        @NotEmpty String password,
        @NotEmpty String nickname,
        Role role
) {

    SignupCommand toCommand() {
        return new SignupCommand(email, password, nickname, role);
    }

}
