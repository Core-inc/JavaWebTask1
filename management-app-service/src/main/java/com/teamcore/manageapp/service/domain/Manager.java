package com.teamcore.manageapp.service.domain;

import lombok.Data;

public @Data class Manager extends User {

    private Manager() {}

    private Manager(Manager manager) {
        super(manager);
        this.setRole(Role.MANAGER);
    }

    public static Builder newBuilder() {
        return new Manager().new Builder();
    }

    public class Builder extends User.Builder<Builder> {

        private Builder() {}

        public Manager build() {
            return new Manager(Manager.this);
        }

    }


}
