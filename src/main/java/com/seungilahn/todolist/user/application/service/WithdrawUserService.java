package com.seungilahn.todolist.user.application.service;

import com.seungilahn.todolist.common.UseCase;
import com.seungilahn.todolist.user.application.port.in.WithdrawUserUseCase;
import com.seungilahn.todolist.user.application.port.out.SaveUserPort;
import com.seungilahn.todolist.user.domain.User;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
class WithdrawUserService implements WithdrawUserUseCase {

    private final SaveUserPort saveUserPort;

    WithdrawUserService(SaveUserPort saveUserPort) {
        this.saveUserPort = saveUserPort;
    }

    @Override
    public void withdraw(User user) {
        // TODO: Backup user data before withdrawal
        user.withdraw();
        saveUserPort.saveUser(user);
    }

}
