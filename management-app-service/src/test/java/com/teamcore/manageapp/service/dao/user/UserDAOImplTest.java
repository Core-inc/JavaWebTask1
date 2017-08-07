package com.teamcore.manageapp.service.dao.user;

import com.teamcore.manageapp.service.config.TestServiceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestServiceConfig.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"classpath:db/init_schema.sql", "classpath:db/init_data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:db/cleanup.sql"})
})
public class UserDAOImplTest {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void getByEmail() throws Exception {

    }

    @Test
    public void addSkill() throws Exception {

    }

    @Test
    public void getAll() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void delete1() throws Exception {

    }

    @Test
    public void getAllByName() throws Exception {

    }

    @Test
    public void getRoleByUserId() throws Exception {

    }

}