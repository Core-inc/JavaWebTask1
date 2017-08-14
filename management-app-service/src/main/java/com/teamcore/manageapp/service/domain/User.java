package com.teamcore.manageapp.service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@EqualsAndHashCode(exclude = {"role"})
public @Data class User {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    @JsonIgnore
    private String password;

    @NotNull
    @JsonIgnore
    private String salt;

    @NotNull
    private Boolean enabled;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotNull
    private Role role;

    protected User() {}

    protected User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.email = user.email;
        this.password = user.password;
        this.salt = user.salt;
        this.enabled = user.enabled;
        this.createdAt = user.createdAt;
        this.updatedAt = user.updatedAt;
        this.role = user.role;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }

        if(obj == this) return true;

        User user = (User) obj;
        return new EqualsBuilder()
                .append(id, user.id)
                .append(name, user.name)
                .append(email, user.email)
                .append(salt, user.salt)
                .append(enabled, user.enabled)
                .append(createdAt, user.createdAt)
                .append(updatedAt, user.updatedAt)
                .append(role, user.role)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(email)
                .append(salt)
                .append(enabled)
                .append(createdAt)
                .append(updatedAt)
                .append(role)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("email", email)
                .append("enabled", enabled)
                .append("role", role)
                .toString();
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder<T extends Builder> {

        protected Builder() {}

        public T setId(Long id) {
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

        public T setRole(Role role) {
            User.this.setRole(role);
            return (T) this;
        }

        public User build() {
            return new User(User.this);
        }

    }
}
