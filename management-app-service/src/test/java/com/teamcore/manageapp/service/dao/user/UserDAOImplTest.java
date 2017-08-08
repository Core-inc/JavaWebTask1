package com.teamcore.manageapp.service.dao.user;

import com.teamcore.manageapp.service.config.TestServiceConfig;
import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.Skill;
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

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    private JdbcTemplate jdbcTemplate;

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
        User user = TestFactory.createDefaultUser();

        User savedUser = userDAO.saveOrUpdate(user);

        assertEquals(rowCount + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users"));
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
    public void getByEmail() throws Exception {
        String email = "first@epam.com";

        User user = userDAO.getByEmail(email);

        assertEquals(email, user.getEmail());
    }

    @Test
    public void addSkill() throws Exception {
        Integer amount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_developers_skills");
        Skill skill = Skill.newBuilder()
                .setId(1L)
                .setName("java")
                .build();

        userDAO.addSkill(userDAO.getById(1L), skill);

        assertEquals(amount + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_developers_skills"));
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