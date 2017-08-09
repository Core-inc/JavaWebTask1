package com.teamcore.manageapp.service.dao.impl;

import com.teamcore.manageapp.service.config.TestServiceConfig;
import com.teamcore.manageapp.service.dao.UserDAO;
import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;
import com.teamcore.manageapp.service.utils.TestFactory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getById() throws Exception {
        Long id = 1L;
        String email = "first@epam.com";

        User user = userDAO.getById(id);

        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
    }

    @Test
    public void save() throws Exception {
        Integer rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users");
        User user = TestFactory.createDefaultNewUser();

        //insert in db
        User savedUser = userDAO.save(user);

        //check that db inserted
        User returnedUser = userDAO.getById(savedUser.getId());

        assertEquals(rowCount + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users"));
        assertEquals(savedUser.getId(), returnedUser.getId());
        assertEquals(savedUser.getEmail(), returnedUser.getEmail());
    }

    @Test
    public void update() throws Exception {
        User user = TestFactory.createDefaultExistedUser();

        String newName = "Mr. Anderson";

        //update name
        user.setName(newName);

        userDAO.update(user);

        //check that db updated
        User returnedUser = userDAO.getById(user.getId());

        assertEquals(user.getName(), returnedUser.getName());
    }

    @Test
    public void getByEmail() throws Exception {
        String email = "first@epam.com";

        User user = userDAO.getByEmail(email);

        assertEquals(email, user.getEmail());
    }

    @Test
    public void getAll() throws Exception {
        Integer amount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users");

        List<User> users = userDAO.getAll();

        assertEquals(Integer.valueOf(amount), Integer.valueOf(users.size()));
    }

    @Test
    public void delete() throws Exception {
        Integer rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users");

        userDAO.delete(1L);

        assertEquals(rowCount - 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users"));
    }

    @Test
    public void getAllByName() throws Exception {
        Integer amount = 1;

        List<User> users = userDAO.getAllByName("first");

        assertEquals(Integer.valueOf(amount), Integer.valueOf(users.size()));

    }

    @Test
    public void getRoleByUserId() throws Exception {
        Integer roleId = 0;
        String roleName = "admin";

        Role role = userDAO.getRoleByUserId(1L);

        assertEquals(roleId, role.getRoleId());
        assertEquals(roleName, role.getName());
    }

}