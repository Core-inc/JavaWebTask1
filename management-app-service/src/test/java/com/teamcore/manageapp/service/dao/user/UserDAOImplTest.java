package com.teamcore.manageapp.service.dao.user;

import com.teamcore.manageapp.service.TestFactory;
import com.teamcore.manageapp.service.config.TestConfig;
import com.teamcore.manageapp.service.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UserDAOImplTest {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Test
    @Sql(scripts={"classpath:db/cleanup.sql",
            "classpath:db/init_schema.sql",
            "classpath:db/init_data.sql"})
    public void saveAndGetUser() throws InterruptedException {
        User user = TestFactory.createDefaultUser();

        User dbUser = userDAO.addUser(user);
        assertNotNull(dbUser);

        //fetch from database test
        dbUser = userDAO.getUserById(dbUser.getId());
        assertNotNull(dbUser);
        //test fields
        assertEquals(user.getName(), dbUser.getName());
        assertEquals(user.getEmail(), dbUser.getEmail());
        assertEquals(user.getCreatedAt(), dbUser.getCreatedAt());
    }

}