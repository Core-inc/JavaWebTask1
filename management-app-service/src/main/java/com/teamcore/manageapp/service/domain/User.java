package com.teamcore.manageapp.service.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(exclude = {"role"})
public @Data class User extends AbstractDomainClass {
    private String name;
    private String email;
    private String password;
    private String salt;
    private Boolean enabled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer roleId;
}
