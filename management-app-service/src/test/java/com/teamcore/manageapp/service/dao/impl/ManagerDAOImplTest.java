package com.teamcore.manageapp.service.dao.impl;

import com.teamcore.manageapp.service.config.TestServiceConfig;
import com.teamcore.manageapp.service.dao.AdminDAO;
import com.teamcore.manageapp.service.dao.ManagerDAO;
import com.teamcore.manageapp.service.domain.Admin;
import com.teamcore.manageapp.service.domain.Manager;
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
public class ManagerDAOImplTest {

    private ManagerDAO managerDAO;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setManagerDAO(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }

    @Test
    public void save() throws Exception {
        Integer rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users");

        Manager user = TestFactory.createDefaultNewManager();

        //insert in db
        Manager savedUser = managerDAO.save(user);

        //check that db inserted
        Manager returnedUser = managerDAO.getById(savedUser.getId());

        assertEquals(rowCount + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users"));
        assertEquals(savedUser.getId(), returnedUser.getId());
        assertEquals(savedUser.getEmail(), returnedUser.getEmail());
    }

    @Test
    public void update() throws Exception {
        Manager user = TestFactory.createDefaultExistedManager();

        String newName = "Mr. Anderson";

        //update name
        user.setName(newName);

        managerDAO.update(user);

        //check that db updated
        Manager returnedUser = managerDAO.getById(user.getId());

        assertEquals(user.getName(), returnedUser.getName());
    }

    @Test
    public void delete() throws Exception {
        Integer rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users");

        managerDAO.delete(2L);

        assertEquals(rowCount - 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users"));
    }

    @Test
    public void getById() throws Exception {
        Long id = 2L;
        String email = "second@epam.com";

        Manager user = managerDAO.getById(id);

        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
    }

    @Test
    public void getByEmail() throws Exception {
        String email = "second@epam.com";

        Manager user = managerDAO.getByEmail(email);

        assertEquals(email, user.getEmail());
    }

    @Test
    public void getAll() throws Exception {
        Integer amount = 1;

        Manager manager = TestFactory.createDefaultExistedManager();

        List<Manager> users = managerDAO.getAll();

        assertEquals(amount, Integer.valueOf(users.size()));

        assertEquals(manager.getId(), users.get(0).getId());
        assertEquals(manager.getEmail(), users.get(0).getEmail());
    }

    @Test
    public void getAllByName() throws Exception {
        Integer amount = 1;

        Manager manager = TestFactory.createDefaultExistedManager();

        List<Manager> users = managerDAO.getAllByName(manager.getName());

        assertEquals(amount, Integer.valueOf(users.size()));

        assertEquals(manager.getId(), users.get(0).getId());
        assertEquals(manager.getEmail(), users.get(0).getEmail());

    }

}