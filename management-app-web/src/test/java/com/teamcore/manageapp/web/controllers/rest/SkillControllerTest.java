package com.teamcore.manageapp.web.controllers.rest;

import com.teamcore.manageapp.service.domain.Skill;
import com.teamcore.manageapp.service.services.skill.SkillService;
import com.teamcore.manageapp.service.services.skill.exceptions.SkillNotFoundException;
import com.teamcore.manageapp.service.services.skill.exceptions.SkillsNotFoundException;
import com.teamcore.manageapp.web.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class SkillControllerTest {

    private MockMvc mockMvc;
    private SkillService mockService;

    @Before
    public void testSuiteSetup() {

        //setup mock skill repository
        mockService = Mockito.mock(SkillService.class);

        //setup http controller
        SkillController controller = new SkillController(mockService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


//    @Before
//    public void testSetup() {
//        //reset mock
//        reset(mockService);
//    }


    @Test
    //test if all skills not found
    public void testFindAll_NotFound() throws Exception {

        //setup mock skill repository
        when(mockService.findAll())
                .thenThrow(new SkillsNotFoundException());

        //assert expectations
        mockMvc.perform(get("/skill/list"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error_code")
                        .value(RestError.SKILLS_NOT_FOUND_CODE));

        verify(mockService, times(1)).findAll();
        verifyNoMoreInteractions(mockService);
    }

    @Test
    //test if all skills found
    public void testFindAll_Found() throws Exception {

        List<Skill> expectedSkills = Arrays.asList(
                Skill.newBuilder()
                        .setId(1)
                        .setName("Java").build(),
                Skill.newBuilder()
                        .setId(2)
                        .setName("Python").build()
        );

        //setup mock skill repository
        when(mockService.findAll())
                .thenReturn(expectedSkills);


        //assert expectations
        mockMvc.perform(get("/skill/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Java"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Python"));

        verify(mockService, times(1)).findAll();
        verifyNoMoreInteractions(mockService);
    }

    @Test
    //test if skill not found
    public void testFindSkillById_NotFound() throws Exception {

        //setup mock skill repository
        when(mockService.findById(1))
                .thenThrow(new SkillNotFoundException(1));

        //assert expectations
        mockMvc.perform(get("/skill/{id}", 1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error_code")
                        .value(RestError.SKILL_NOT_FOUND_CODE));

        verify(mockService, times(1)).findById(1);
        verifyNoMoreInteractions(mockService);
    }

    @Test
    public void testFindSkillById_Found() throws Exception {

        Skill expectedSkill = Skill.newBuilder()
                .setId(1)
                .setName("Java").build();

        //setup mock skill repository
        when(mockService.findById(1))
                .thenReturn(expectedSkill);

        //assert expectations
        mockMvc.perform(get("/skill/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Java"));

        verify(mockService, times(1)).findById(1);
        verifyNoMoreInteractions(mockService);
    }

}
