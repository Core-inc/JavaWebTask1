package com.teamcore.manageapp.service.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude = "users")
public @Data class Role extends AbstractDomainClass {

    private Integer roleId;

    private String name;
}
