package com.teamcore.site;

import com.teamcore.site.domain.Role;
import com.teamcore.site.domain.User;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;

public class TestFactory {

    public static User createDefaultUser() {
        User user = new User();
        user.setName("John");

        Role role = new Role();
        role.setRoleId(0);
        role.setName("admin");
        //user.setRole(role);

        //TODO delete!!!
        user.setRoleId(role.getRoleId());

        user.setEmail("john@epam.com");
        user.setPassword("123456");
        user.setSalt("wkfemwkpfmwek");
        user.setCreatedAt(LocalDateTime.now());

        return user;
    }
}
