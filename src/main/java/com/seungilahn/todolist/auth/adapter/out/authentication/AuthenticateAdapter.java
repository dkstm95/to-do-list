package com.seungilahn.todolist.auth.adapter.out.authentication;

import com.seungilahn.todolist.auth.application.port.out.AuthenticatePort;
import com.seungilahn.todolist.common.AuthenticationAdapter;
import com.seungilahn.todolist.user.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@AuthenticationAdapter
class AuthenticateAdapter implements AuthenticatePort {

    private final AuthenticationManager authenticationManager;

    AuthenticateAdapter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public User authenticate(String email, String password) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        return (User) authenticate.getPrincipal();
    }

}
