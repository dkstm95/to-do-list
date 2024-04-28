package com.seungilahn.todolist.auth.application.service;

import com.seungilahn.todolist.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.todolist.auth.application.port.in.SigninCommand;
import com.seungilahn.todolist.auth.application.port.in.SigninUseCase;
import com.seungilahn.todolist.auth.application.port.out.AuthenticatePort;
import com.seungilahn.todolist.auth.application.port.out.LoadTokenPort;
import com.seungilahn.todolist.auth.application.port.out.TokenProviderPort;
import com.seungilahn.todolist.auth.domain.AuthenticationTokens;
import com.seungilahn.todolist.auth.domain.Token;
import com.seungilahn.todolist.common.UseCase;
import com.seungilahn.todolist.user.domain.User;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
class SigninService implements SigninUseCase {

    private final LoadTokenPort loadTokenPort;
    private final TokenProviderPort tokenProviderPort;
    private final AuthenticatePort authenticatePort;

    SigninService(LoadTokenPort loadTokenPort, TokenProviderPort tokenProviderPort, AuthenticatePort authenticatePort) {
        this.loadTokenPort = loadTokenPort;
        this.tokenProviderPort = tokenProviderPort;
        this.authenticatePort = authenticatePort;
    }

    @Override
    public AuthenticationResponse signin(SigninCommand command) {

        User authenticatedUser = authenticatePort.authenticate(command.email(), command.password());

        AuthenticationTokens tokens = tokenProviderPort.generateAuthenticationTokens(authenticatedUser.getEmail());

        Token token = loadTokenPort.loadToken(authenticatedUser.getId());
        token.refresh(tokens.refreshToken());

        return new AuthenticationResponse(tokens.accessToken(), tokens.refreshToken());
    }

}
