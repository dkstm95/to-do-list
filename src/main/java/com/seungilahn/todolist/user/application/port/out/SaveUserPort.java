package com.seungilahn.todolist.user.application.port.out;

import com.seungilahn.todolist.user.domain.User;

public interface SaveUserPort {

    User saveUser(User user);

}
