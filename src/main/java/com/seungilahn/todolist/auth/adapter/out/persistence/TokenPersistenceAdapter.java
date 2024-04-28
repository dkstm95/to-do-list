package com.seungilahn.todolist.auth.adapter.out.persistence;

import com.seungilahn.todolist.auth.application.port.out.LoadTokenPort;
import com.seungilahn.todolist.auth.application.port.out.SaveTokenPort;
import com.seungilahn.todolist.auth.domain.Token;
import com.seungilahn.todolist.common.PersistenceAdapter;
import jakarta.persistence.EntityNotFoundException;

@PersistenceAdapter
class TokenPersistenceAdapter implements LoadTokenPort, SaveTokenPort {

    private final SpringDataTokenRepository tokenRepository;

    TokenPersistenceAdapter(SpringDataTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Token loadToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Token loadToken(Long userId) {
        return tokenRepository.findByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

}
