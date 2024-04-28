package com.seungilahn.todolist.configuration;

import com.seungilahn.todolist.auth.application.port.out.PasswordEncoderPort;
import com.seungilahn.todolist.user.application.port.out.LoadUserPort;
import com.seungilahn.todolist.user.domain.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
class CustomAuthenticationProvider implements AuthenticationProvider {

    private final LoadUserPort loadUserPort;
    private final PasswordEncoderPort passwordEncoderPort;

    CustomAuthenticationProvider(LoadUserPort loadUserPort, PasswordEncoderPort passwordEncoderPort) {
        this.loadUserPort = loadUserPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = loadUserPort.findUser(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!passwordEncoderPort.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
