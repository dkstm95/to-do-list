package com.seungilahn.todolist.auth.application.port.in;

public interface RefreshTokenUseCase {

    AuthenticationResponse tokenRefresh(RefreshTokenCommand command);

}
