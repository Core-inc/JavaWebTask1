package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.SkillDAO;
import com.teamcore.manageapp.service.domain.Skill;
import com.teamcore.manageapp.service.service.SkillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SkillServiceImplTest {
    @MockBean
    private SkillDAO skillDAO;

    @InjectMocks
    private SkillServiceImpl skillService;

    @Test
    public void getAll() throws Exception {
        Skill firstSkill = Skill.newBuilder()
                .setId(0L)
                .setName("java")
                .build();
        Skill secondSkill = Skill.newBuilder()
                .setId(1L)
                .setName("js")
                .build();

        when(skillDAO.getAllSkills()).thenReturn(Arrays.asList(firstSkill, secondSkill));

        List<Skill> skills = skillService.getAll();


    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

}