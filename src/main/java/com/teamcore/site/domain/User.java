package com.teamcore.site.domain;

import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "t_users")
public @Data class User extends AbstractDomainClass {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String salt;
    @Column(nullable = false)
    private DateTime createdAt;

    private DateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
}
