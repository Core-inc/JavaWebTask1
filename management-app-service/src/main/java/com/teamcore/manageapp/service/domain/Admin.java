package com.teamcore.manageapp.service.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

public @Data class Admin extends User {

    private Admin() {}

    private Admin(Admin admin) {
        super(admin);
        this.setRole(Role.ADMIN);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }

        if(obj == this) return true;

        return super.equals(obj);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .toString();
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
