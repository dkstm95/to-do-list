package com.seungilahn.todolist.auth.application.service;

import com.seungilahn.todolist.auth.application.port.in.SigninCommand;
import com.seungilahn.todolist.auth.application.port.out.AuthenticatePort;
import com.seungilahn.todolist.auth.application.port.out.LoadTokenPort;
import com.seungilahn.todolist.auth.application.port.out.TokenProviderPort;
import com.seungilahn.todolist.auth.domain.AuthenticationTokens;
import com.seungilahn.todolist.auth.domain.Token;
import com.seungilahn.todolist.common.TokenTestData;
import com.seungilahn.todolist.common.UserTestData;
import com.seungilahn.todolist.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class SigninServiceTest {

    private final LoadTokenPort loadTokenPort =
            Mockito.mock(LoadTokenPort.class);

    private final TokenProviderPort tokenProviderPort =
            Mockito.mock(TokenProviderPort.class);

    private final AuthenticatePort authenticatePort =
            Mockito.mock(AuthenticatePort.class);

    private final SigninService signinService =
            new SigninService(loadTokenPort, tokenProviderPort, authenticatePort);

    @Test
    void when_signin_then_new_refresh_token_saved() {
        // given
        SigninCommand command = new SigninCommand("email", "password");

        User user = UserTestData.defaultUser()
                .withId(1L)
                .withEmail(command.email())
                .build();
        given(authenticatePort.authenticate(command.email(), command.password()))
                .willReturn(user);

        Token token = TokenTestData.defaultToken()
                .withUserId(user.getId())
                .withToken("old_refresh_token")
                .build();
        given(loadTokenPort.loadToken(user.getId()))
                .willReturn(token);

        AuthenticationTokens tokens = new AuthenticationTokens(
                "new_access_token",
                "new_refresh_token"
        );
        given(tokenProviderPort.generateAuthenticationTokens(command.email()))
                .willReturn(tokens);

        // when
        signinService.signin(command);

        // then
        assertThat(token.isSameRefreshToken(tokens.refreshToken()))
                .isTrue();
    }

}