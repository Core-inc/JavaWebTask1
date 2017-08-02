package com.teamcore.site.dao.user;

import com.teamcore.site.TestFactory;
import com.teamcore.site.dao.skill.SkillDAO;
import com.teamcore.site.domain.Skill;
import com.teamcore.site.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDAOImplTest {
    private UserDAO userDAO;
    private SkillDAO skillDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setSkillDAO(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }

    @Test
    public void saveUser() {
        User user = TestFactory.createDefaultUser();

        assertNotNull(userDAO.addUser(user).getId());

    }

    @Test
    public void addUserSkills() {
        User user = TestFactory.createDefaultUser();

        Skill skill = new Skill();
        skill.setName("java");

        assertNotNull(skillDAO.addSkill(skill).getId());

        user.addSkill(skill);

        User userSaved = userDAO.addUser(user);

        assertTrue(userSaved.getSkills().contains(skill));

        userSaved = userDAO.getUserById(userSaved.getId());

        System.out.println();
    }
}