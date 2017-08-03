package com.teamcore.site.dao.user;

import com.teamcore.site.TestFactory;
import com.teamcore.site.dao.skill.SkillDAO;
import com.teamcore.site.domain.Skill;
import com.teamcore.site.domain.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
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