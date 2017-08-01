package com.teamcore.site.dao.user;

import com.teamcore.site.TestFactory;
import com.teamcore.site.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDAOImplTest {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Test
    public void saveUser() {
        User user = TestFactory.createDefaultUser();

        assertNotNull(userDAO.addUser(user).getId());

    }
}