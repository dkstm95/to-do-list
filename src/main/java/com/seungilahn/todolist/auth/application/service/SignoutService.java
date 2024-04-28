package com.seungilahn.todolist.auth.application.service;

import com.seungilahn.todolist.auth.application.port.in.SignoutCommand;
import com.seungilahn.todolist.auth.application.port.in.SignoutUseCase;
import com.seungilahn.todolist.auth.application.port.out.LoadTokenPort;
import com.seungilahn.todolist.auth.domain.Token;
import com.seungilahn.todolist.common.UseCase;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
class SignoutService implements SignoutUseCase {

    private final LoadTokenPort loadTokenPort;

    SignoutService(LoadTokenPort loadTokenPort) {
        this.loadTokenPort = loadTokenPort;
    }

    @Override
    public void signout(SignoutCommand command) {
        Token token = loadTokenPort.loadToken(command.refreshToken());
        token.revoke();
    }

}
