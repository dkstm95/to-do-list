package com.seungilahn.todolist.auth.application.port.out;

import com.seungilahn.todolist.user.domain.User;

public interface AuthenticatePort {

    User authenticate(String email, String password);

}
