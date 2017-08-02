package com.teamcore.site;

import com.teamcore.site.domain.User;

import java.sql.Date;
import java.util.Calendar;

public class TestFactory {

    public static User createDefaultUser() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@epam.com");
        user.setPassword("123456");
        user.setSalt("wkfemwkpfmwek");
        user.setCreatedAt(new Date(Calendar.getInstance().getTimeInMillis()));

        return user;
    }
}
