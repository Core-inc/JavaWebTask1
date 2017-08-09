package com.teamcore.manageapp.service.domain;

import lombok.Data;

public @Data class Skill {
    private Long id;
    private String name;

    private Skill() {}

    private Skill(Skill skill) {
        this.id = skill.id;
        this.name = skill.name;
    }

    public static Builder newBuilder() {
        return new Skill().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setId(Long id) {
            Skill.this.setId(id);
            return this;
        }

        public Builder setName(String name) {
            Skill.this.setName(name);
            return this;
        }

        public Skill build() {
            return new Skill(Skill.this);
        }

    }
}
