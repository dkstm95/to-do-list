package com.seungilahn.todolist.user.application.port.out;

import com.seungilahn.todolist.user.domain.User;

import java.util.Optional;

public interface LoadUserPort {

    Optional<User> findUser(String email);
    User loadUser(String email);

}
