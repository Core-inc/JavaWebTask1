package com.teamcore.manageapp.service.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class Developer extends User {
    private List<Skill> skills = new ArrayList<>();

    private Developer() {}

    private Developer(Developer developer) {
        super(developer);
        this.skills = developer.skills;
    }

    public static Builder newBuilder() {
       return new Developer().new Builder();
    }

    public class Builder extends User.Builder<Builder> {

        private Builder() {}

        public Builder setSkills(List<Skill> skillList) {
            Developer.this.skills = skillList;
            return this;
        }

        public Developer build() {
            return new Developer(Developer.this);
        }

    }


}
