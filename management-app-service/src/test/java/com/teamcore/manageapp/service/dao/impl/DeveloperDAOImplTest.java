package com.teamcore.manageapp.service.dao.impl;

import com.teamcore.manageapp.service.config.TestServiceConfig;
import com.teamcore.manageapp.service.dao.DeveloperDAO;
import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.User;
import com.teamcore.manageapp.service.utils.TestFactory;
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
public class DeveloperDAOImplTest {

    private DeveloperDAO developerDAO;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setDeveloperDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    @Test
    public void save() throws Exception {
        Integer rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users");

        Developer user = TestFactory.createDefaultNewDeveloper();

        //insert in db
        Developer savedUser = developerDAO.save(user);

        //check that db inserted
        Developer returnedUser = developerDAO.getById(savedUser.getId());

        assertEquals(rowCount + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users"));
        assertEquals(savedUser.getId(), returnedUser.getId());
        assertEquals(savedUser.getEmail(), returnedUser.getEmail());
    }

    @Test
    public void update() throws Exception {
        Developer user = TestFactory.createDefaultExistedDeveloper();

        String newName = "Mr. Anderson";

        //update name
        user.setName(newName);

        developerDAO.update(user);

        //check that db updated
        User returnedUser = developerDAO.getById(user.getId());

        assertEquals(user.getName(), returnedUser.getName());
    }

    @Test
    public void delete() throws Exception {
        Integer rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users");

        developerDAO.delete(3L);

        assertEquals(rowCount - 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users"));
    }

    @Test
    public void getById() throws Exception {
        Long id = 3L;
        String email = "third@epam.com";

        User user = developerDAO.getById(id);

        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
    }

    @Test
    public void getByEmail() throws Exception {
        String email = "third@epam.com";

        Developer user = developerDAO.getByEmail(email);

        assertEquals(email, user.getEmail());
    }

    @Test
    public void getAll() throws Exception {
        Integer amount = 1;

        Developer developer = TestFactory.createDefaultExistedDeveloper();

        List<Developer> users = developerDAO.getAll();

        assertEquals(amount, Integer.valueOf(users.size()));

        assertEquals(developer.getId(), users.get(0).getId());
        assertEquals(developer.getEmail(), users.get(0).getEmail());
    }

    @Test
    public void getAllByName() throws Exception {
        Integer amount = 1;

        Developer developer = TestFactory.createDefaultExistedDeveloper();

        List<Developer> users = developerDAO.getAllByName(developer.getName());

        assertEquals(Integer.valueOf(amount), Integer.valueOf(users.size()));

        assertEquals(developer.getId(), users.get(0).getId());
        assertEquals(developer.getEmail(), users.get(0).getEmail());

    }

}