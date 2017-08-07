package com.epam.managementapp.service.dao.project.impl;


import com.epam.managementapp.service.dao.project.ProjectDAO;
import com.epam.managementapp.service.domain.Project;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import com.epam.managementapp.service.config.TestConfig;
import org.springframework.test.context.web.WebAppConfiguration;


import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ProjectDAOImplTest {

    @Resource
    @Autowired
    ProjectDAO projectDAO;

    @Autowired
    public void setProjectDAO(ProjectDAO projectDAO) {
        // this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.projectDAO = projectDAO;
    }



    @Test
    public void testAddNewProject() throws Exception {
        int idOfNewProject = projectDAO.addNewProject(new Project("testOuterName","testInnerName","http://test","testing",new Date("07/08/2017"),new Date("07/08/2017")));

        Assert.assertNotEquals(0,idOfNewProject);

    }




    @Test
    public void testFindById() throws Exception {


        int id = 1;
        Project project = projectDAO.findById(id);

        Assert.assertNotNull(project);
        Assert.assertEquals(project.getId(), id);
    }



    @Test
    public void testDeleteById() throws Exception {


        int id = 2;
        projectDAO.deleteById(id);

        Project delProject = projectDAO.findById(2);

        Assert.assertNull(delProject);
    }




    @Test
    public void testFindByInnerName() throws Exception {


        String innername = "testInnerName";
        Project project = projectDAO.findByInnerName(innername);

        Assert.assertNotNull(project);
        Assert.assertEquals(project.getInnerName(), innername);
    }



    @Test
    public void testDeleteByInnerName() throws Exception {


        String innername = "testInnerName";
        projectDAO.deleteByInnerName(innername);

        Project project = projectDAO.findByInnerName("testInnerName");

        Assert.assertNull(project);
    }


    @Test
    public void testUpdateProject() throws Exception {

        int id = 6;
        Project oldProject = projectDAO.findById(id);
        Project updProject = projectDAO.findById(id);
        updProject.setStatus("updated");

        projectDAO.updateProject(updProject);

        Assert.assertNotEquals(oldProject,updProject);
        Assert.assertEquals(projectDAO.findById(id).getStatus(),"updated");

    }

    @Test
    public void testViewAllProjects() throws Exception {

        List<Project> list = projectDAO.viewAllProjects();

        Assert.assertNotNull(list);
    }


}






