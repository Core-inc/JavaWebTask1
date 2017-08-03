package com.teamcore.site.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_skills")
@EqualsAndHashCode(exclude = "users")
public @Data class Skill extends AbstractDomainClass {
    private String name;

}
