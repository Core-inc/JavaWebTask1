package com.teamcore.site.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_users")
@EqualsAndHashCode(exclude = {"role"})
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
    private Boolean enabled;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(name = "c_user_group_id", nullable = false)
    private Integer roleId;

//    @ManyToOne
//    @JoinColumn(name = "c_user_group_id")
//    private Role role;

}
