package com.seungilahn.todolist.auth.application.service;

import com.seungilahn.todolist.auth.application.port.in.SignoutCommand;
import com.seungilahn.todolist.auth.application.port.out.LoadTokenPort;
import com.seungilahn.todolist.auth.domain.Token;
import com.seungilahn.todolist.common.TokenTestData;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class SignoutServiceTest {

    private final LoadTokenPort loadTokenPort =
            Mockito.mock(LoadTokenPort.class);

    private final SignoutService signoutService =
            new SignoutService(loadTokenPort);

    @Test
    void when_signout_then_token_is_revoked() {
        // given
        Token token = TokenTestData.defaultToken()
                .withToken("token")
                .withRevoked(false)
                .withExpired(false)
                .build();

        given(loadTokenPort.loadToken("token"))
                .willReturn(token);

        SignoutCommand command = new SignoutCommand("token");

        // when
        signoutService.signout(command);

        // then
        assertThat(token.isValid()).isFalse();
    }

}