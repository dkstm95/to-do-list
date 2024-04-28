package com.seungilahn.todolist.auth.application.port.out;

import com.seungilahn.todolist.auth.domain.AuthenticationTokens;

public interface TokenProviderPort {

    AuthenticationTokens generateAuthenticationTokens(String email);
    String extractEmail(String token);
    boolean isValidToken(String token, String email);

}
