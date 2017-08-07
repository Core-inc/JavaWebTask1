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

        Role role = new Role();
        role.setId(1);
        role.setRoleId(0);
        role.setName("admin");
        //user.setRole(role);

        user.setRole(role);



        return user;
    }
}
