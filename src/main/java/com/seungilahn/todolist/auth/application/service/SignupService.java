package com.seungilahn.todolist.auth.application.service;

import com.seungilahn.todolist.auth.application.port.in.AuthenticationResponse;
import com.seungilahn.todolist.auth.application.port.in.SignupCommand;
import com.seungilahn.todolist.auth.application.port.in.SignupUseCase;
import com.seungilahn.todolist.auth.application.port.out.PasswordEncoderPort;
import com.seungilahn.todolist.auth.application.port.out.SaveTokenPort;
import com.seungilahn.todolist.auth.application.port.out.TokenProviderPort;
import com.seungilahn.todolist.auth.domain.AuthenticationTokens;
import com.seungilahn.todolist.auth.domain.Token;
import com.seungilahn.todolist.auth.domain.TokenType;
import com.seungilahn.todolist.common.UseCase;
import com.seungilahn.todolist.user.application.port.out.LoadUserPort;
import com.seungilahn.todolist.user.application.port.out.SaveUserPort;
import com.seungilahn.todolist.user.domain.Role;
import com.seungilahn.todolist.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
class SignupService implements SignupUseCase {

    private static final Logger log = LoggerFactory.getLogger(SignupService.class);

    private final SaveTokenPort saveTokenPort;
    private final TokenProviderPort tokenProviderPort;
    private final LoadUserPort loadUserPort;
    private final SaveUserPort saveUserPort;
    private final PasswordEncoderPort passwordEncoderPort;

    SignupService(SaveTokenPort saveTokenPort, TokenProviderPort tokenProviderPort, LoadUserPort loadUserPort, SaveUserPort saveUserPort, PasswordEncoderPort passwordEncoderPort) {
        this.saveTokenPort = saveTokenPort;
        this.tokenProviderPort = tokenProviderPort;
        this.loadUserPort = loadUserPort;
        this.saveUserPort = saveUserPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public AuthenticationResponse signup(SignupCommand command) {

        validateEmailIsNotInUse(command.email());

        User savedNewUser = createNewUserAndSave(command.email(), command.password(), command.nickname(), command.role());

        AuthenticationTokens tokens = generateTokens(savedNewUser);

        return new AuthenticationResponse(tokens.accessToken(), tokens.refreshToken());
    }

    private void validateEmailIsNotInUse(String email) {
        if (loadUserPort.findUser(email).isPresent()) {
            log.warn("Email already in use: {}", email);
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }

    private User createNewUserAndSave(String email, String password, String nickname, Role role) {
        User newUser = User.withoutId(
                email,
                passwordEncoderPort.encode(password),
                nickname,
                role,
                true
        );
        return saveUserPort.saveUser(newUser);
    }

    private AuthenticationTokens generateTokens(User savedNewUser) {
        AuthenticationTokens tokens = tokenProviderPort.generateAuthenticationTokens(savedNewUser.getEmail());
        Token token = Token.withoutId(tokens.accessToken(), savedNewUser.getId(), TokenType.BEARER, false, false);
        saveTokenPort.saveToken(token);
        return tokens;
    }

}
