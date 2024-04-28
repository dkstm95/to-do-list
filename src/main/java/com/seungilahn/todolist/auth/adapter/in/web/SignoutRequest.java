package com.seungilahn.todolist.auth.adapter.in.web;

import com.seungilahn.todolist.auth.application.port.in.SignoutCommand;
import jakarta.validation.constraints.NotEmpty;

record SignoutRequest(
        @NotEmpty String refreshToken
) {

    SignoutCommand toCommand() {
        return new SignoutCommand(refreshToken);
    }

}
