package com.seungilahn.todolist.user.adapter.in.web;

import com.seungilahn.todolist.common.LoginUser;
import com.seungilahn.todolist.common.WebAdapter;
import com.seungilahn.todolist.user.application.port.in.WithdrawUserUseCase;
import com.seungilahn.todolist.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
@PreAuthorize("hasRole('USER')")
class WithdrawUserController {

    private final WithdrawUserUseCase useCase;

    WithdrawUserController(WithdrawUserUseCase useCase) {
        this.useCase = useCase;
    }

    @DeleteMapping("/api/v1/users")
    public ResponseEntity<?> withdraw(@LoginUser User user) {
        useCase.withdraw(user);
        return ResponseEntity.ok().build();
    }

}
