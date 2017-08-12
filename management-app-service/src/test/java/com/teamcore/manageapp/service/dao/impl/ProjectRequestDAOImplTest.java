package com.teamcore.manageapp.service.dao.impl;


import com.teamcore.manageapp.service.config.TestServiceConfig;
import com.teamcore.manageapp.service.dao.ProjectDAO;
import com.teamcore.manageapp.service.dao.ProjectRequestDAO;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.ProjectRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * Class provides methods for testing ProjectDAOImpl. Use DatabaseConfig.class for creating context.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestServiceConfig.class})
//@ContextConfiguration(classes = TestTestConfig.class)

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"classpath:db/init_schema.sql", "classpath:db/init_data.sql"}),
        //  scripts = {"classpath:db/init_schema.sql", "classpath:db/test_init_data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:db/cleanup.sql"})
})


public class ProjectRequestDAOImplTest {

    ProjectRequestDAO projectRequestDAO;

    @Autowired
    public void setProjectRequestDAO(ProjectRequestDAO projectRequestDAO) {

        this.projectRequestDAO = projectRequestDAO;
    }

    /**
     * testing AddNewProjectRequest method from ProjectRequestDAOImpl.
     * adding new project request with specified fields .Checking it's id not null
     */

    @Test
    public void testAddNewProjectRequest() throws Exception {
        ProjectRequest newProjectRequest = ProjectRequest.getProjectRequest();
        newProjectRequest.setExternalName("project4");
        newProjectRequest.setSpecLink("To make mobile app");
        newProjectRequest.setCustomerName("Alex");
        newProjectRequest.setCustomerEmail("alex@mail.ru");

        long idProjectRequestFromDb = projectRequestDAO.addNewProjectRequest(newProjectRequest).getId();

        Assert.assertEquals(4,idProjectRequestFromDb);

    }

    /**
     * testing FindById method from ProjectRequestDAOImpl.
     * looking for project request with specified id .Checking if this project request not null and if it's id is equal with specified
     */
    @Test
    public void testFindById() throws Exception {


        Long id = 1L;
        ProjectRequest projectRequest = projectRequestDAO.findById(id);

        Assert.assertNotNull(projectRequest);
        Assert.assertEquals(projectRequest.getId(), id);
    }


    /**
     * testing DeleteById method from ProjectRequestDAOImpl.
     * looking for project request with specified id, deleting it .Checking that project request with specified id is null
     */
    @Test
    public void testDeleteById() throws Exception {


        Long id = 2L;
        projectRequestDAO.deleteById(id);

        ProjectRequest delProjectRequest = projectRequestDAO.findById(2L);

        Assert.assertNull(delProjectRequest);
    }



    /**
     * testing FindByCustomerName method from ProjectRequestDAOImpl.
     * looking for project with specified internalName.Checking that project not null and it's internalName equals with specified
     */
    @Test
    public void testFindByCustomerName() {


        String customerName = "Ben";
        ProjectRequest projectRequest = projectRequestDAO.findByCustomerName(customerName);


        Assert.assertNotNull(projectRequest);
        Assert.assertEquals(projectRequest.getCustomerName(), customerName);
    }

    /**
     * testing ViewAllProjects method from ProjectDAOImpl.
     * listing all projects. Check that it is not null
     */
    @Test
    public void testViewAllProjectRequests() throws Exception {

        List<ProjectRequest> list = projectRequestDAO.viewAllProjectRequests();

        Assert.assertNotNull(list);
    }


}

