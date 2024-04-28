package com.seungilahn.todolist.user.application.port.in;

import com.seungilahn.todolist.user.domain.User;

public interface WithdrawUserUseCase {

    void withdraw(User user);

}
