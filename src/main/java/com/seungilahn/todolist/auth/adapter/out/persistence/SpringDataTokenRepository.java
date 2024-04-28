package com.seungilahn.todolist.auth.adapter.out.persistence;

import com.seungilahn.todolist.auth.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SpringDataTokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByUserId(Long userId);
    Optional<Token> findByToken(String token);

}
