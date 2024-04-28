package com.seungilahn.todolist.auth.application.service;

import com.seungilahn.todolist.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.todolist.auth.application.port.in.RefreshTokenCommand;
import com.seungilahn.todolist.auth.application.port.in.RefreshTokenUseCase;
import com.seungilahn.todolist.auth.application.port.out.LoadTokenPort;
import com.seungilahn.todolist.auth.application.port.out.TokenProviderPort;
import com.seungilahn.todolist.auth.domain.AuthenticationTokens;
import com.seungilahn.todolist.auth.domain.Token;
import com.seungilahn.todolist.common.UseCase;
import com.seungilahn.todolist.user.application.port.out.LoadUserPort;
import com.seungilahn.todolist.user.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@Transactional
class RefreshTokenService implements RefreshTokenUseCase {

    private final LoadTokenPort loadTokenPort;
    private final TokenProviderPort tokenProviderPort;
    private final LoadUserPort loadUserPort;

    RefreshTokenService(LoadTokenPort loadTokenPort, TokenProviderPort tokenProviderPort, LoadUserPort loadUserPort) {
        this.loadTokenPort = loadTokenPort;
        this.tokenProviderPort = tokenProviderPort;
        this.loadUserPort = loadUserPort;
    }

    @Override
    public AuthenticationResponse tokenRefresh(RefreshTokenCommand command) {

        User user = loadUserByRefreshToken(command.refreshToken());

        Token token = loadTokenPort.loadToken(user.getId());

        validateOldRefreshToken(command.refreshToken(), token, user);

        AuthenticationTokens tokens = tokenProviderPort.generateAuthenticationTokens(user.getEmail());

        token.refresh(tokens.refreshToken());

        return new AuthenticationResponse(tokens.accessToken(), tokens.refreshToken());
    }

    private User loadUserByRefreshToken(String jwt) {
        String userEmail = Optional.ofNullable(tokenProviderPort.extractEmail(jwt))
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token."));
        return loadUserPort.loadUser(userEmail);
    }

    private void validateOldRefreshToken(String oldRefreshToken, Token token, User user) {
        if (!token.isSameRefreshToken(oldRefreshToken) ||
                !tokenProviderPort.isValidToken(oldRefreshToken, user.getEmail()) ||
                !token.isValid()) {
            throw new IllegalArgumentException("Invalid refresh token.");
        }
    }

}
