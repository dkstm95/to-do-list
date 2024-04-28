package com.seungilahn.todolist.user.application.service;

import com.seungilahn.todolist.common.UserTestData;
import com.seungilahn.todolist.user.application.port.out.SaveUserPort;
import com.seungilahn.todolist.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class WithdrawUserServiceTest {

    private final SaveUserPort saveUserPort =
            Mockito.mock(SaveUserPort.class);

    private final WithdrawUserService withdrawUserService =
            new WithdrawUserService(saveUserPort);

    @Test
    void when_withdrawUser_then_user_not_enabled() {
        // given
        User user = UserTestData.defaultUser()
                .withEnabled(true)
                .build();

        // when
        withdrawUserService.withdraw(user);

        // then
        assertThat(user.isEnabled()).isFalse();
    }

}