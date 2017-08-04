package com.teamcore.manageapp.service;


import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
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
