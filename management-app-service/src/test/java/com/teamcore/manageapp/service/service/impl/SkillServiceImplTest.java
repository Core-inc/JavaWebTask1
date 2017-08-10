package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.SkillDAO;
import com.teamcore.manageapp.service.domain.Skill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceImplTest {
    @Mock
    private SkillDAO skillDAO;

    @InjectMocks
    private SkillServiceImpl skillService;

    @Test
    public void getAll() throws Exception {
        Skill firstSkill = newTestSkill();
        Skill secondSkill = newTestSkill();
        secondSkill.setId(1L);

        when(skillDAO.getAllSkills()).thenReturn(Arrays.asList(firstSkill, secondSkill));

        List<Skill> skills = skillService.getAll();

        assertEquals(2, skills.size());
        assertEquals(skills.get(0), firstSkill);

        verify(skillDAO, times(1)).getAllSkills();
        verifyNoMoreInteractions(skillDAO);
    }

    @Test
    public void getById() throws Exception {
        Skill testSkill = newTestSkill();

        when(skillDAO.getSkillById(0L)).thenReturn(testSkill);


        Skill skill = skillService.getById(0L);

        assertEquals(testSkill, skill);

        verify(skillDAO, times(1)).getSkillById(0L);
        verifyNoMoreInteractions(skillDAO);
    }

    @Test
    public void save() throws Exception {
        Skill skill = newTestSkill();
        skill.setId(null);
        Skill savedSkill = newTestSkill();

        when(skillDAO.addSkill(skill)).thenReturn(savedSkill);

        Skill resultSkill= skillService.save(skill);

        assertNotEquals(skill, resultSkill);

        verify(skillDAO, times(1)).addSkill(skill);
        verifyNoMoreInteractions(skillDAO);
    }

    @Test
    public void update() throws Exception {
        Skill skill = newTestSkill();

        when(skillDAO.updateSkill(skill)).thenReturn(skill);

        skillService.update(skill);

        verify(skillDAO, times(1)).updateSkill(skill);
        verifyNoMoreInteractions(skillDAO);
    }

    @Test
    public void delete() throws Exception {
        skillService.delete(0L);

        verify(skillDAO, times(1)).deleteSkill(0L);
        verifyNoMoreInteractions(skillDAO);
    }

    private Skill newTestSkill() {
        return Skill.newBuilder()
                .setId(0L)
                .setName("java")
                .build();
    }

}