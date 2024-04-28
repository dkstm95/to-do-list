package com.seungilahn.todolist.auth.application.port.in;

public interface SignupUseCase {

    AuthenticationResponse signup(SignupCommand command);

}
