package com.seungilahn.todolist.auth.application.port.out;

import com.seungilahn.todolist.auth.domain.Token;

public interface SaveTokenPort {

    void saveToken(Token token);

}
