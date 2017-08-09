package com.teamcore.manageapp.service.domain;

import lombok.Data;

public @Data class Admin extends User {

    private Admin() {}

    private Admin(Admin admin) {
        super(admin);
        this.setRole(Role.ADMIN);
    }

    public static Builder newBuilder() {
        return new Admin().new Builder();
    }

    public class Builder extends User.Builder<Builder> {

        private Builder() {}

        public Admin build() {
            return new Admin(Admin.this);
        }

    }


}
