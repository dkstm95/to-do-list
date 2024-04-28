package com.seungilahn.todolist.user.adapter.out.persistence;

import com.seungilahn.todolist.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface SpringDataUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
