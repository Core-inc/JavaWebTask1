package com.teamcore.manageapp.service.dao.project;


import com.teamcore.manageapp.service.config.TestServiceConfig;
import com.teamcore.manageapp.service.domain.Project;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import javax.annotation.Resource;
import java.util.Date;
import java.util.List;



/**
 * Class provides methods for testing ProjectDAOImpl. Use DatabaseConfig.class for creating context.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestServiceConfig.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"classpath:db/init_schema.sql", "classpath:db/init_data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:db/cleanup.sql"})
})

public class ProjectDAOImplTest {

    @Resource
    @Autowired
    ProjectDAO projectDAO;

    @Autowired
    public void setProjectDAO(ProjectDAO projectDAO) {

        this.projectDAO = projectDAO;
    }

    /**
     * testing AddNewProject method from ProjectDAOImpl.
     * adding new project with specified fields .Checking it's id not null
     */

    @Test
    public void testAddNewProject() throws Exception {
        Project newProject = Project.getProject();
        newProject.setExternalName("testExternalName2");
        newProject.setInternalName("testInternalName2");
        newProject.setSpecLink("http://test2");
        newProject.setStatus(0);
        newProject.setCreatedAt(new Date("08/07/2017"));
        newProject.setUpdatedAt(new Date("08/07/2017"));

        long idOfNewProject = projectDAO.addNewProject(newProject);

        Assert.assertNotEquals(0,idOfNewProject);

    }

    /**
     * testing FindById method from ProjectDAOImpl.
     * looking for project with specified id .Checking if this project not null and if it's id is equal with specified
     */
    @Test
    public void testFindById() throws Exception {


        Long id = 1L;
        Project project = projectDAO.findById(id);

        Assert.assertNotNull(project);
        Assert.assertEquals(project.getId(), id);
    }


    /**
     * testing DeleteById method from ProjectDAOImpl.
     * looking for project with specified id, deleting it .Checking that project with specified id is null
     */
    @Test
    public void testDeleteById() throws Exception {


        Long id = 2L;
        projectDAO.deleteById(id);

        Project delProject = projectDAO.findById(2L);

        Assert.assertNull(delProject);
    }



    /**
     * testing FindByInternalName method from ProjectDAOImpl.
     * looking for project with specified internalName.Checking that project not null and it's internalName equals with specified
     */
    @Test
    public void testFindByInternalName() throws Exception {


        String internalName = "testInternalName2";
        Project project = projectDAO.findByInternalName(internalName);

        Assert.assertNotNull(project);
        Assert.assertEquals(project.getInternalName(), internalName);
    }


    /**
     * testing DeleteByInternalName method from ProjectDAOImpl.
     * looking for project with specified internalName.Checking that project with specified internalName is null
     */
    @Test
    public void testDeleteByInternalName() throws Exception {


        String internalName = "testInternalName2";
        projectDAO.deleteByInternalName(internalName);

        Project project = projectDAO.findByInternalName("testInternalName2");

        Assert.assertNull(project);
    }

    /**
     * testing UpdateProject method from ProjectDAOImpl.
     * taking project that we want to update, change it's info. Check if old project not equals with new one and if
     * updated fields are equals with specified
     */
    @Test
    public void testUpdateProject() throws Exception {

        Long id = 3L;
        Project oldProject = projectDAO.findById(id);
        Project updProject = projectDAO.findById(id);
        updProject.setStatus(1);

        projectDAO.updateProject(updProject);

        Assert.assertNotEquals(oldProject,updProject);
        Assert.assertEquals(projectDAO.findById(id).getStatus(),1);

    }

    /**
     * testing ViewAllProjects method from ProjectDAOImpl.
     * listing all projects. Check that it is not null
     */
    @Test
    public void testViewAllProjects() throws Exception {

        List<Project> list = projectDAO.viewAllProjects();

        Assert.assertNotNull(list);
    }


}

