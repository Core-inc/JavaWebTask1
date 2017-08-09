package com.teamcore.manageapp.service.domain;

import lombok.Data;

public @Data class Developer extends User {

    private Developer() {}

    private Developer(Developer developer) {
        super(developer);
        this.setRole(Role.DEVELOPER);
    }

    public static Builder newBuilder() {
       return new Developer().new Builder();
    }

    public class Builder extends User.Builder<Builder> {

        private Builder() {}

        public Developer build() {
            return new Developer(Developer.this);
        }

    }


}
