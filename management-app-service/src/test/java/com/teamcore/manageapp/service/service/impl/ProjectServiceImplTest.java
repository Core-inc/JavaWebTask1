package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.config.TestServiceConfig;
import com.teamcore.manageapp.service.dao.ProjectDAO;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.service.ProjectService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides methods for testing ProjectDAOImpl. Use DatabaseConfig.class for creating context.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {TestServiceConfig.class})
//@ContextConfiguration(classes = TestTestConfig.class)

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"classpath:db/init_schema.sql", "classpath:db/init_data.sql"}),
        //  scripts = {"classpath:db/init_schema.sql", "classpath:db/test_init_data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:db/cleanup.sql"})
})

public class ProjectServiceImplTest {


    @Mock
    ProjectDAO projectDAO;

    // используем аанотацию @InjectMocks для создания mock объекта
    @InjectMocks
    ProjectServiceImpl projectService = new ProjectServiceImpl(projectDAO);


    /**
     * testing save method from ProjectServiceImpl.
     * adding new project with specified fields .Checking that it is equals with project, saved in db
     */


    @Test
    public void testSave() throws Exception {
        Project newProject = Project.getProject();
        newProject.setExternalName("testExternalName2");
        newProject.setInternalName("testInternalName2");
        newProject.setSpecLink("http://test2");
        newProject.setStatus(0);
        newProject.setCreatedAt(LocalDateTime.parse("2015-08-11 15:00:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        newProject.setUpdatedAt(LocalDateTime.parse("2015-08-11 15:00:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // int numberOfAddedRows = projectDAO.addNewProject(newProject);
        Project mockProject = Project.getProject();
        newProject.setId(4L);
        newProject.setExternalName("testExternalName2");
        newProject.setInternalName("testInternalName2");
        newProject.setSpecLink("http://test2");
        newProject.setStatus(0);
        newProject.setCreatedAt(LocalDateTime.parse("2015-08-11 15:00:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        newProject.setUpdatedAt(LocalDateTime.parse("2015-08-11 15:00:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Mockito.when(projectService.save(newProject)).thenReturn(mockProject);

        Assert.assertEquals(mockProject, projectService.save(newProject));
        Mockito.verify(projectDAO).addNewProject(newProject);

    }
    /*
    @After
    @Sql(scripts = {"classpath:db/cleanup.sql"})
    */

    /**
     * testing GetById method from ProjectServiceImpl.
     * looking for project with specified id .Checking if this project not null and if it's id is equal with specified
     */
    @Test
    public void testGetById() throws Exception {


        Long id = 1L;
        // Project project = projectService.getById(id);
        Project project = Project.getProject();
        project.setId(1L);
        project.setExternalName("testExternalName");
        project.setInternalName("testInternalName");
        project.setSpecLink("http://test");
        project.setStatus(0);
        project.setCreatedAt(LocalDateTime.parse("2016-01-19 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        project.setUpdatedAt(LocalDateTime.parse("2017-10-27 02:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Mockito.when(projectService.getById(id)).thenReturn(project);

        Assert.assertEquals(project, projectService.getById(id));
        Mockito.verify(projectDAO).findById(id);

    }

    /**
     * testing delete method from ProjectServiceImpl.
     * looking for project with specified id, deleting it .Checking that project with specified id is null
     */
    @Test
    public void testDelete() throws Exception {


        Long id = 2L;
        projectService.delete(id);
        Mockito.verify(projectDAO).deleteById(id);

    }

    /**
     * testing FindByInternalName method from ProjectServiceImpl.
     * looking for project with specified internalName.Checking that project not null and it's internalName equals with specified
     */
    @Test
    public void testFindByInternalName() {


        String internalName = "testInternalName";

        Project project = Project.getProject();
        project.setId(1L);
        project.setExternalName("testExternalName");
        project.setInternalName("testInternalName");
        project.setSpecLink("http://test");
        project.setStatus(0);
        project.setCreatedAt(LocalDateTime.parse("2016-01-19 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        project.setUpdatedAt(LocalDateTime.parse("2017-10-27 02:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


        Mockito.when(projectService.findByInternalName(internalName)).thenReturn(project);

        Assert.assertEquals(project, projectService.findByInternalName(internalName));
        Mockito.verify(projectDAO).findByInternalName(internalName);
    }

    /**
     * testing DeleteByInternalName method from ProjectServiceImpl.
     * looking for project with specified internalName.Checking that project with specified internalName is null
     */
    @Test
    public void testDeleteByInternalName() throws Exception {


        String internalName = "testInternalName2";

        projectService.deleteByInternalName(internalName);
        Mockito.verify(projectDAO).deleteByInternalName(internalName);
    }

    /**
     * testing Update method from ProjectServiceImpl.
     * taking project that we want to update, change it's info. Check if old project not equals with new one and if
     * updated fields are equals with specified
     */
    @Test
    public void testUpdate() throws Exception {


        Project projectUpd = Project.getProject();
        projectUpd.setId(3L);
        projectUpd.setExternalName("game_tanks");
        projectUpd.setInternalName("world_of_tanks");
        projectUpd.setSpecLink("http://tanks");
        projectUpd.setStatus(1);
        projectUpd.setCreatedAt(LocalDateTime.parse("2017-10-12 01:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        projectUpd.setUpdatedAt(LocalDateTime.parse("2017-10-12 01:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));



        Mockito.when(projectService.update(projectUpd)).thenReturn(projectUpd);

        Assert.assertEquals(projectUpd, projectService.update(projectUpd));
        Mockito.verify(projectDAO).updateProject(projectUpd);

    }

    /**
     * testing getAll method from ProjectServiceImpl.
     * listing all projects. Check that it is not null
     */
    @Test
    public void testGetAll() throws Exception {

        List<Project> list = new ArrayList<Project>();


        Project project1 = Project.getProject();
        project1.setId(1L);
        project1.setExternalName("testExternalName");
        project1.setInternalName("testInternalName");
        project1.setSpecLink("http://test");
        project1.setStatus(0);
        project1.setCreatedAt(LocalDateTime.parse("2016-01-19 15:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        project1.setUpdatedAt(LocalDateTime.parse("2017-10-27 02:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Project project2 = Project.getProject();
        project2.setId(2L);
        project2.setExternalName("web_site");
        project2.setInternalName("web_site_1");
        project2.setSpecLink("http://site");
        project2.setStatus(0);
        project2.setCreatedAt(LocalDateTime.parse("2017-10-15 02:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        project2.setUpdatedAt(LocalDateTime.parse("2017-10-15 02:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Project project3 = Project.getProject();
        project3.setId(3L);
        project3.setExternalName("game_tanks");
        project3.setInternalName("world_of_tanks");
        project3.setSpecLink("http://tanks");
        project3.setStatus(1);
        project3.setCreatedAt(LocalDateTime.parse("2017-10-12 01:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        project3.setUpdatedAt(LocalDateTime.parse("2017-10-12 01:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Project project4 = Project.getProject();
        project4.setId(4L);
        project4.setExternalName("testExternalName2");
        project4.setInternalName("testInternalName2");
        project4.setSpecLink("http://test2");
        project4.setStatus(0);
        project4.setCreatedAt(LocalDateTime.parse("2015-08-11 15:00:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        project4.setUpdatedAt(LocalDateTime.parse("2015-08-11 15:00:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        list.add(project1);
        list.add(project2);
        list.add(project3);
        list.add(project4);

        Mockito.when(projectService.getAll()).thenReturn(list);

        Assert.assertEquals(list, projectService.getAll());
        Mockito.verify(projectDAO).viewAllProjects();
    }


}
