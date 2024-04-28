package com.seungilahn.todolist.auth.adapter.in.web;

import com.seungilahn.todolist.auth.application.port.in.SigninCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

record SigninRequest(
        @NotEmpty @Email String email,
        @NotEmpty String password
) {

    SigninCommand toCommand() {
        return new SigninCommand(email, password);
    }

}
