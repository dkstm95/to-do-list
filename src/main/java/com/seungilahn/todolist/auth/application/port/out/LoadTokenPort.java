package com.seungilahn.todolist.auth.application.port.out;

import com.seungilahn.todolist.auth.domain.Token;

public interface LoadTokenPort {

    Token loadToken(String token);
    Token loadToken(Long userId);

}
