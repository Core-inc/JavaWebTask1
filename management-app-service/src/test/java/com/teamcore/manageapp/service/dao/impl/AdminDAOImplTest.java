package com.teamcore.manageapp.service.dao.impl;

import com.teamcore.manageapp.service.config.TestServiceConfig;
import com.teamcore.manageapp.service.dao.AdminDAO;
import com.teamcore.manageapp.service.domain.Admin;
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
public class AdminDAOImplTest {

    private AdminDAO adminDAO;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Test
    public void save() throws Exception {
        Integer rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users");

        Admin user = TestFactory.createDefaultNewAdmin();

        //insert in db
        Admin savedUser = adminDAO.save(user);

        //check that db inserted
        Admin returnedUser = adminDAO.getById(savedUser.getId());

        assertEquals(rowCount + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users"));
        assertEquals(savedUser.getId(), returnedUser.getId());
        assertEquals(savedUser.getEmail(), returnedUser.getEmail());
    }

    @Test
    public void update() throws Exception {
        Admin user = TestFactory.createDefaultExistedAdmin();

        String newName = "Mr. Anderson";

        //update name
        user.setName(newName);

        adminDAO.update(user);

        //check that db updated
        Admin returnedUser = adminDAO.getById(user.getId());

        assertEquals(user.getName(), returnedUser.getName());
    }

    @Test
    public void delete() throws Exception {
        Integer rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users");

        adminDAO.delete(1L);

        assertEquals(rowCount - 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_users"));
    }

    @Test
    public void getById() throws Exception {
        Long id = 1L;
        String email = "first@epam.com";

        Admin user = adminDAO.getById(id);

        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
    }

    @Test
    public void getByEmail() throws Exception {
        String email = "first@epam.com";

        Admin user = adminDAO.getByEmail(email);

        assertEquals(email, user.getEmail());
    }

    @Test
    public void getAll() throws Exception {
        Integer amount = 1;

        Admin admin = TestFactory.createDefaultExistedAdmin();

        List<Admin> users = adminDAO.getAll();

        assertEquals(amount, Integer.valueOf(users.size()));

        assertEquals(admin.getId(), users.get(0).getId());
        assertEquals(admin.getEmail(), users.get(0).getEmail());
    }

    @Test
    public void getAllByName() throws Exception {
        Integer amount = 1;

        Admin admin = TestFactory.createDefaultExistedAdmin();

        List<Admin> users = adminDAO.getAllByName(admin.getName());

        assertEquals(amount, Integer.valueOf(users.size()));

        assertEquals(admin.getId(), users.get(0).getId());
        assertEquals(admin.getEmail(), users.get(0).getEmail());

    }

}