package com.seungilahn.todolist.auth.application.port.in;

public interface SigninUseCase {

    AuthenticationResponse signin(SigninCommand command);

}
