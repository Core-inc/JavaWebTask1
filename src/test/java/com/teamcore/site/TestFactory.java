package com.teamcore.site;

import com.teamcore.site.domain.User;
import org.joda.time.DateTime;

/**
 * Created by igoz on 31.07.17.
 */

public class TestFactory {

    public static User createDefaultUser() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@epam.com");
        user.setPassword("123456");
        user.setSalt("wkfemwkpfmwek");
        user.setCreatedAt(new DateTime());

        return user;
    }
}
