package com.teamcore.manageapp.service.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude = "users")
public @Data class Skill extends AbstractDomainClass {
    private String name;

    private Skill() {}

    private Skill(Skill skill) {
        super(skill);
        this.name = skill.name;
    }

    public static Builder newBuilder() {
        return new Skill().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setId(Integer id) {
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
