package com.teamcore.manageapp.service.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

public @Data class Role {
    @NotNull
    private Long id;

    @NotNull
    private Integer roleId;

    @NotNull
    private String name;

    private Role() { }

    private Role(Role role) {
        this.id = role.id;
        this.roleId = role.roleId;
        this.name = role.name;
    }

    public static Builder newBuilder() {
        return new Role().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setId(Long id) {
            Role.this.setId(id);
            return this;
        }

        public Builder setRoleId(Integer roleId) {
            Role.this.setRoleId(roleId);
            return this;
        }

        public Builder setName(String name) {
            Role.this.setName(name);
            return this;
        }

        public Role build() {
            return new Role(Role.this);
        }
    }
}
