package com.teamcore.site.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by igoz on 26.07.17.
 */

@Entity
@Table(name = "t_user_groups")
@EqualsAndHashCode(exclude = "users")
public @Data class Role extends AbstractDomainClass {
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        user.setRole(this);
        this.users.add(user);
    }

    public void removeUser(User user) {
        user.setRole(null);
        this.users.remove(user);
    }
}
