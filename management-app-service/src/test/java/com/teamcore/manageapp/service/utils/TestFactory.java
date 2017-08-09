package com.teamcore.manageapp.service.utils;


import com.teamcore.manageapp.service.domain.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestFactory {

    public static User createDefaultNewUser() {
       return User.newBuilder()
                .setName("John")
                .setEmail("john@epam.com")
                .setPassword("123456")
                .setSalt("wkfemwkpfmwek")
                .setCreatedAt(LocalDateTime.now())
                .setRole(Role.MANAGER)
                .build();

    }

    public static Admin createDefaultNewAdmin() {
        return Admin.newBuilder()
                .setName("John")
                .setEmail("john@epam.com")
                .setPassword("123456")
                .setSalt("wkfemwkpfmwek")
                .setCreatedAt(LocalDateTime.now())
                .build();
    }


    public static Manager createDefaultNewManager() {
        return Manager.newBuilder()
                .setName("John")
                .setEmail("john@epam.com")
                .setPassword("123456")
                .setSalt("wkfemwkpfmwek")
                .setCreatedAt(LocalDateTime.now())
                .build();
    }

    public static Developer createDefaultNewDeveloper() {
        return Developer.newBuilder()
                .setName("John")
                .setEmail("john@epam.com")
                .setPassword("123456")
                .setSalt("wkfemwkpfmwek")
                .setCreatedAt(LocalDateTime.now())
                .build();
    }

    public static User createDefaultExistedUser() {
        return User.newBuilder()
                .setId(1L)
                .setName("first")
                .setEmail("first@epam.com")
                .setPassword("qwe")
                .setSalt("qwe")
                .setCreatedAt(LocalDateTime.now())
                .setRole(Role.ADMIN)
                .build();
    }


    public static Admin createDefaultExistedAdmin() {
        return Admin.newBuilder()
                .setId(1L)
                .setName("first")
                .setEmail("first@epam.com")
                .setPassword("qwe")
                .setSalt("qwe")
                .setCreatedAt(LocalDateTime.now())
                .build();
    }

    public static Manager createDefaultExistedManager() {
        return Manager.newBuilder()
                .setId(2L)
                .setName("second")
                .setEmail("second@epam.com")
                .setPassword("qwe")
                .setSalt("qwe")
                .setCreatedAt(LocalDateTime.now())
                .build();
    }

    public static Developer createDefaultExistedDeveloper() {
        return Developer.newBuilder()
                .setId(3L)
                .setName("third")
                .setEmail("third@epam.com")
                .setPassword("qwe")
                .setSalt("qwe")
                .setCreatedAt(LocalDateTime.now())
                .build();
    }

}
