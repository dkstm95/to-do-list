package com.seungilahn.todolist.auth.application.service;

import com.seungilahn.todolist.auth.application.port.in.SignupCommand;
import com.seungilahn.todolist.auth.application.port.out.PasswordEncoderPort;
import com.seungilahn.todolist.auth.application.port.out.SaveTokenPort;
import com.seungilahn.todolist.auth.application.port.out.TokenProviderPort;
import com.seungilahn.todolist.common.UserTestData;
import com.seungilahn.todolist.user.application.port.out.LoadUserPort;
import com.seungilahn.todolist.user.application.port.out.SaveUserPort;
import com.seungilahn.todolist.user.domain.Role;
import com.seungilahn.todolist.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

class SignupServiceTest {

    private final SaveTokenPort saveTokenPort =
            Mockito.mock(SaveTokenPort.class);

    private final TokenProviderPort tokenProviderPort =
            Mockito.mock(TokenProviderPort.class);

    private final LoadUserPort loadUserPort =
            Mockito.mock(LoadUserPort.class);

    private final SaveUserPort saveUserPort =
            Mockito.mock(SaveUserPort.class);

    private final PasswordEncoderPort passwordEncoderPort =
            Mockito.mock(PasswordEncoderPort.class);

    private final SignupService signupService =
            new SignupService(saveTokenPort, tokenProviderPort, loadUserPort, saveUserPort, passwordEncoderPort);

    @Test
    void throw_exception_when_email_is_already_in_use() {
        // given
        User existingUser = UserTestData.defaultUser()
                .withEmail("test@email.com")
                .build();

        given(loadUserPort.findUser(existingUser.getEmail()))
                .willReturn(Optional.of(existingUser));

        SignupCommand command = new SignupCommand(
                "test@email.com",
                "password",
                "nickname",
                Role.USER);

        // when
        // then
        assertThatThrownBy(() -> signupService.signup(command))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 이메일입니다.");

    }

}