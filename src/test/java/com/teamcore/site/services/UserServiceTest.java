package com.teamcore.site.services;

import com.teamcore.site.domain.Role;
import com.teamcore.site.domain.User;
import com.teamcore.site.services.role.RoleService;
import com.teamcore.site.services.user.UserService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    public void saveWithoutNullableColumns() {
        User user = new User();
        user.setName("Tester");
        user.setEmail("tester@test.com");
        user.setPassword("qwerty");
        user.setSalt("salt");
        user.setCreatedAt(LocalDateTime.now());

        assertNull(user.getId());

        userService.saveOrUpdate(user);

        assertNotNull(user.getId());
    }

    @Test
    public void roleModifying() {
        User user = new User();
        user.setName("Tester");
        user.setEmail("tester@test.com");
        user.setPassword("qwerty");
        user.setSalt("salt");
        user.setCreatedAt(LocalDateTime.now());

        Role role = new Role();
        role.setName("user");

        userService.saveOrUpdate(user);
        roleService.saveOrUpdate(role);

        //assertNull(user.getRole());

        List<User> userList = (List<User>) userService.listAll();
        userList.forEach(el -> {
            role.addUser(el);
            userService.saveOrUpdate(el);
        });

        userList = (List<User>) userService.listAll();
        //userList.forEach(el -> assertEquals("user", el.getRole().getName()));
    }

}
