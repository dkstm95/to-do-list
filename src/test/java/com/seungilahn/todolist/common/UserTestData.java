package com.seungilahn.todolist.common;

import com.seungilahn.todolist.user.domain.Role;
import com.seungilahn.todolist.user.domain.User;

public class UserTestData {

    public static UserTestDataBuilder defaultUser() {
        return new UserTestDataBuilder()
                .withId(1L)
                .withEmail("test@email.com")
                .withPassword("password")
                .withNickname("testNickname")
                .withRole(Role.USER)
                .withEnabled(true);
    }

    public static class UserTestDataBuilder {
        private Long id;
        private String email;
        private String password;
        private String nickname;
        private Role role;
        private boolean enabled;

        public UserTestDataBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserTestDataBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserTestDataBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserTestDataBuilder withNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserTestDataBuilder withRole(Role role) {
            this.role = role;
            return this;
        }

        public UserTestDataBuilder withEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public User build() {
            return User.withId(id, email, password, nickname, role, enabled);
        }
    }

}
