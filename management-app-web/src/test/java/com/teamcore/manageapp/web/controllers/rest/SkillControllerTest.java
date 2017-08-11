package com.teamcore.manageapp.web.controllers.rest;

import com.teamcore.manageapp.service.domain.Skill;
import com.teamcore.manageapp.service.service.SkillService;
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
        when(mockService.getAll())
                .thenThrow(new SkillsNotFoundException());

        //assert expectations
        mockMvc.perform(get("/skills"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error_code")
                        .value(RestError.SKILLS_NOT_FOUND_CODE));

        verify(mockService, times(1)).getAll();
        verifyNoMoreInteractions(mockService);
    }

    @Test
    //test if all skills found
    public void testFindAll_Found() throws Exception {

        List<Skill> expectedSkills = Arrays.asList(
                Skill.newBuilder()
                        .setId(1L)
                        .setName("Java").build(),
                Skill.newBuilder()
                        .setId(2L)
                        .setName("Python").build()
        );

        //setup mock skill repository
        when(mockService.getAll())
                .thenReturn(expectedSkills);


        //assert expectations
        mockMvc.perform(get("/skills"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Java"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Python"));

        verify(mockService, times(1)).getAll();
        verifyNoMoreInteractions(mockService);
    }

    @Test
    //test if skill not found
    public void testFindSkillById_NotFound() throws Exception {

        //setup mock skill repository
        when(mockService.getById(1L))
                .thenThrow(new SkillNotFoundException(1));

        //assert expectations
        mockMvc.perform(get("/skills/{id}", 1))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error_code")
                        .value(RestError.SKILL_NOT_FOUND_CODE));

        verify(mockService, times(1)).getById(1L);
        verifyNoMoreInteractions(mockService);
    }

    @Test
    public void testFindSkillById_Found() throws Exception {

        Skill expectedSkill = Skill.newBuilder()
                .setId(1L)
                .setName("Java").build();

        //setup mock skill repository
        when(mockService.getById(1L))
                .thenReturn(expectedSkill);

        //assert expectations
        mockMvc.perform(get("/skills/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Java"));

        verify(mockService, times(1)).getById(1L);
        verifyNoMoreInteractions(mockService);
    }

//    @Test
//    public void testFindAllDevelopersById_NotFound() throws Exception {
//
//        //setup mock skill repository
//        when(mockService.findAllDevelopers(1))
//                .thenThrow(new SkillDevelopersNotFoundException());
//
//        //assert expectations
//        mockMvc.perform(get("/skills/{skillId}/developers", 1))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.error_code")
//                        .value(RestError.SKILL_DEVELOPERS_NOT_FOUND_CODE));
//
//        verify(mockService, times(1)).findAllDevelopers(1);
//        verifyNoMoreInteractions(mockService);
//    }
//
//    @Test
//    //TODO implement test
//    public void testFindAllDevelopersById_Found() throws Exception {
//
//    }

//    @Test
//    public void testFindFreeDevelopersById_NotFound() throws Exception {
//
//        //setup mock skill repository
//        when(mockService.findFreeDevelopers(1))
//                .thenThrow(new SkillDevelopersNotFoundException());
//
//        //assert expectations
//        mockMvc.perform(get("/skills/{skillId}/developers/free", 1))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.error_code")
//                        .value(RestError.SKILL_DEVELOPERS_NOT_FOUND_CODE));
//
//        verify(mockService, times(1)).findFreeDevelopers(1);
//        verifyNoMoreInteractions(mockService);
//    }

//    @Test
//    //TODO implement test
//    public void testFindFreeDevelopersById_Found() {
//
//    }
//
//    @Test
//    public void testFindProjectsById_NotFound() throws Exception {
//
//        //setup mock skill repository
//        when(mockService.findProjects(1))
//                .thenThrow(new SkillProjectsNotFoundException());
//
//        //assert expectations
//        mockMvc.perform(get("/skills/{skillId}/projects", 1))
//                .andExpect(status().isNotFound())
//                .andExpect(jsonPath("$.error_code")
//                        .value(RestError.SKILL_PROJECTS_NOT_FOUND));
//
//        verify(mockService, times(1)).findProjects(1);
//        verifyNoMoreInteractions(mockService);
//    }
//
//    @Test
//    //TODO implement test
//    public void testFindProjectsById_Found() {
//
//    }
}
