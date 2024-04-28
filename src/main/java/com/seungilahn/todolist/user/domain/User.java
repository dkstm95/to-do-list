package com.seungilahn.todolist.user.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.Collection;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    protected User() {
    }

    private User(Long id, String email, String password, String nickname, Role role, boolean enabled) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.enabled = enabled;
    }

    /**
     * Creates an {@link User} entity without an ID. Use to create a new entity that is not yet persisted.
     */
    public static User withoutId(String email, String password, String nickname, Role role, boolean enabled) {
        return new User(null, email, password, nickname, role, enabled);
    }

    /**
     * Creates an {@link User} entity with an ID. Use to reconstitute a persisted entity.
     */
    public static User withId(Long id, String email, String password, String nickname, Role role, boolean enabled) {
        return new User(id, email, password, nickname, role, enabled);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void withdraw() {
        this.email = "withdrawn";
        this.password = "withdrawn";
        this.nickname = "withdrawn";
        this.enabled = false;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role.getAuthorities();
    }

}
