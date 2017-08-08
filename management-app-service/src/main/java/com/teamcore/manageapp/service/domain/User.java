package com.teamcore.manageapp.service.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(exclude = {"role"})
public @Data class User extends AbstractDomainClass {
    private String name;
    private String email;
    private String password;
    private String salt;
    private Boolean enabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer roleId;

    protected User() {}

    protected User(User user) {
        super(user);
        this.name = user.name;
        this.email = user.email;
        this.password = user.password;
        this.salt = user.salt;
        this.enabled = user.enabled;
        this.createdAt = user.createdAt;
        this.updatedAt = user.updatedAt;
        this.roleId = user.roleId;
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder<T extends Builder> {

        protected Builder() {}

        public T setId(Integer id) {
            User.this.setId(id);
            return (T) this;
        }

        public T setName(String name) {
            User.this.setName(name);
            return (T) this;
        }

        public T setEmail(String email) {
            User.this.setEmail(email);
            return (T) this;
        }

        public T setPassword(String password) {
            User.this.setPassword(password);
            return (T) this;
        }

        public T setSalt(String salt) {
            User.this.setSalt(salt);
            return (T) this;
        }

        public T setEnabled(Boolean enabled) {
            User.this.setEnabled(enabled);
            return (T) this;
        }

        public T setCreatedAt(LocalDateTime createdAt) {
            User.this.setCreatedAt(createdAt);
            return (T) this;
        }

        public T setUpdatedAt(LocalDateTime updatedAt) {
            User.this.setUpdatedAt(updatedAt);
            return (T) this;
        }

        public T setRoleId(Integer roleId) {
            User.this.setRoleId(roleId);
            return (T) this;
        }

        public User build() {
            return new User(User.this);
        }

    }
}
