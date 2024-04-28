package com.seungilahn.todolist.user.adapter.out.persistence;

import com.seungilahn.todolist.common.PersistenceAdapter;
import com.seungilahn.todolist.user.application.port.out.LoadUserPort;
import com.seungilahn.todolist.user.application.port.out.SaveUserPort;
import com.seungilahn.todolist.user.domain.User;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

@PersistenceAdapter
class UserPersistenceAdapter implements LoadUserPort, SaveUserPort {

    private final SpringDataUserRepository userRepository;

    UserPersistenceAdapter(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User loadUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

}
