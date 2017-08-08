package com.teamcore.manageapp.service.utils;


import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestFactory {

    public static User createDefaultUser() {
        User user = User.newBuilder()
                .setName("John")
                .setEmail("john@epam.com")
                .setPassword("123456")
                .setSalt("wkfemwkpfmwek")
                .setCreatedAt(LocalDateTime.now())
                .build();

        Role role = Role.newBuilder()
                .setId(1L)
                .setRoleId(0)
                .setName("admin")
                .build();

        user.setRole(role);

        return user;
    }
}
