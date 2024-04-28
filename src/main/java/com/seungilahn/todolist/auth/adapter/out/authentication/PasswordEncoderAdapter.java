package com.seungilahn.todolist.auth.adapter.out.authentication;

import com.seungilahn.todolist.auth.application.port.out.PasswordEncoderPort;
import com.seungilahn.todolist.common.AuthenticationAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@AuthenticationAdapter
class PasswordEncoderAdapter implements PasswordEncoderPort {

    private final PasswordEncoder passwordEncoder;

    PasswordEncoderAdapter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
