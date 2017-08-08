package com.teamcore.manageapp.service.dao.skill;

import com.teamcore.manageapp.service.config.TestServiceConfig;
import com.teamcore.manageapp.service.domain.Skill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestServiceConfig.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"classpath:db/init_schema.sql", "classpath:db/init_data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:db/cleanup.sql"})
})
public class SkillDAOImplTest {

    private SkillDAO skillDAO;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Autowired
    public void setSkillDAO(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }

    @Test
    public void addSkill() throws Exception {

        Skill newSkill = Skill.newBuilder()
                .setName("Java")
                .build();

        Integer rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_skills");

        //insert in db
        Skill returnedSkill = skillDAO.addSkill(newSkill);

        assertEquals(rowCount + 1,
                JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_skills"));
        assertNotNull(returnedSkill.getId());
        assertEquals(newSkill.getName(), returnedSkill.getName());
    }

    @Test
    public void updateSkill() throws Exception {
        Skill skill = Skill.newBuilder()
                .setId(2)
                .setName("Python")
                .build();

        skill.setName("Golang");

        skillDAO.updateSkill(skill);

        //check that db updated
        Skill returnedSkill = skillDAO.getSkillById(2);

        assertNotNull(returnedSkill);
        assertEquals(skill.getId(), returnedSkill.getId());
        assertEquals(skill.getName(), returnedSkill.getName());
    }

    @Test
    public void deleteSkill() throws Exception {
        Integer rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_skills");

        skillDAO.deleteSkill(1);

        assertEquals(rowCount - 1,
                JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_skills"));
    }

    @Test
    public void getSkillById() throws Exception {
        Integer id = 1;
        String name = "Java";

        Skill skill = skillDAO.getSkillById(id);

        assertEquals(id, skill.getId());
        assertEquals(name, skill.getName());
    }

    @Test
    public void getAllSkills() throws Exception {

        Integer amount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_skills");

        List<Skill> skills = skillDAO.getAllSkills();

        assertEquals(amount, Integer.valueOf(skills.size()));

        Collections.sort(skills, Comparator.comparing(Skill::getId));

        assertEquals(Integer.valueOf(1), skills.get(0).getId());
        assertEquals("Java", skills.get(0).getName());

        assertEquals(Integer.valueOf(2), skills.get(1).getId());
        assertEquals("Python", skills.get(1).getName());

    }

    @Test
    //TODO (do we need this functionality?)
    public void getAllDevelopersBySkillId() throws Exception {

    }

    @Test
    //TODO (do we need this functionality?)
    public void getFreeDevelopersBySkillId() throws Exception {

    }

    @Test
    //TODO (do we need this functionality?)
    public void getProjectsBySkillId() throws Exception {

    }

}