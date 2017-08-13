package com.teamcore.manageapp.service.service.impl;


import com.teamcore.manageapp.service.config.TestServiceConfig;
import com.teamcore.manageapp.service.dao.ProjectDAO;
import com.teamcore.manageapp.service.dao.ProjectRequestDAO;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.ProjectRequest;
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
 * Class provides methods for testing ProjectRequestDAOImpl with mockito.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProjectRequestServiceImplTest {

    @Mock
    ProjectRequestDAO projectRequestDAO;

    // используем аанотацию @InjectMocks для создания mock объекта
    @InjectMocks
    ProjectRequestServiceImpl projectRequestService;// = new ProjectRequestServiceImpl(projectRequestDAO);


    /**
     * testing save method from ProjectRequestServiceImpl.
     * adding new project request with specified fields .Checking that it is equals with project request, saved in db
     */


    @Test
    public void testSave() throws Exception {
        ProjectRequest newProjectRequest = ProjectRequest.getProjectRequest();
        newProjectRequest.setExternalName("project4");
        newProjectRequest.setSpecLink("to make a product");
        newProjectRequest.setCustomerName("George");
        newProjectRequest.setCustomerEmail("goga@mail.ru");

        ProjectRequest mockProjectRequest = ProjectRequest.getProjectRequest();
        mockProjectRequest.setId(4L);
        mockProjectRequest.setExternalName("project4");
        mockProjectRequest.setSpecLink("to make a product");
        newProjectRequest.setCustomerName("George");
        newProjectRequest.setCustomerEmail("goga@mail.ru");


        Mockito.when(projectRequestDAO.addNewProjectRequest(newProjectRequest)).thenReturn(mockProjectRequest);

        Assert.assertEquals(mockProjectRequest, projectRequestService.save(newProjectRequest));
        Mockito.verify(projectRequestDAO).addNewProjectRequest(newProjectRequest);

    }

    /**
     * testing GetById method from ProjectRequestServiceImpl.
     * looking for project request with specified id .Checking if this project request not null and if it's id is equal with specified
     */
    @Test
    public void testGetById() throws Exception {


        Long id = 1L;
        // Project project = projectService.getById(id);
        ProjectRequest projectRequest = ProjectRequest.getProjectRequest();
        projectRequest.setId(1L);
        projectRequest.setExternalName("project1");
        projectRequest.setSpecLink("to make web site");
        projectRequest.setCustomerName("Bob");
        projectRequest.setCustomerEmail("bob@mail.ru");

        Mockito.when(projectRequestDAO.findById(id)).thenReturn(projectRequest);

        Assert.assertEquals(projectRequest, projectRequestService.getById(id));
        Mockito.verify(projectRequestDAO).findById(id);

    }

    /**
     * testing delete method from ProjectRequestServiceImpl.
     * looking for project request with specified id, deleting it .Checking that project request with specified id is null
     */
    @Test
    public void testDelete() throws Exception {


        Long id = 2L;
        projectRequestService.delete(id);
        Mockito.verify(projectRequestDAO).deleteById(id);

    }

    /**
     * testing FindByCustomerName method from ProjectRequestServiceImpl.
     * looking for project request with specified customerName.Checking that project request not null and it's customerName equals with specified
     */
    @Test
    public void testFindByCustomerName() {


        String customerName = "George";

        ProjectRequest projectRequest = ProjectRequest.getProjectRequest();
        projectRequest.setId(1L);
        projectRequest.setExternalName("project1");
        projectRequest.setSpecLink("to make a game");
        projectRequest.setCustomerName("George");
        projectRequest.setCustomerEmail("goga@mail.ru");

        Mockito.when(projectRequestDAO.findByCustomerName(customerName)).thenReturn(projectRequest);

        Assert.assertEquals(projectRequest, projectRequestService.findByCustomerName(customerName));
        Mockito.verify(projectRequestDAO).findByCustomerName(customerName);
    }

    /**
     * testing getAll method from ProjectRequestServiceImpl.
     * listing all project requests. Check that it is not null
     */
    @Test
    public void testGetAll() throws Exception {

        List<ProjectRequest> list = new ArrayList<>();


        ProjectRequest projectRequest1 = ProjectRequest.getProjectRequest();
        projectRequest1.setId(1L);
        projectRequest1.setExternalName("project1");
        projectRequest1.setSpecLink("to make a web site");
        projectRequest1.setCustomerName("Alex");
        projectRequest1.setCustomerEmail("alex@mail.ru");


        ProjectRequest projectRequest2 = ProjectRequest.getProjectRequest();
        projectRequest2.setId(2L);
        projectRequest2.setExternalName("project2");
        projectRequest2.setSpecLink("to make a game");
        projectRequest2.setCustomerName("Ben");
        projectRequest2.setCustomerEmail("ben@mail.ru");

        ProjectRequest projectRequest3 = ProjectRequest.getProjectRequest();
        projectRequest3.setId(3L);
        projectRequest3.setExternalName("project3");
        projectRequest3.setSpecLink("to make a product");
        projectRequest3.setCustomerName("Bob");
        projectRequest3.setCustomerEmail("bob@mail.ru");


        list.add(projectRequest1);
        list.add(projectRequest2);
        list.add(projectRequest3);


        Mockito.when(projectRequestDAO.viewAllProjectRequests()).thenReturn(list);

        Assert.assertEquals(list, projectRequestService.getAll());
        Mockito.verify(projectRequestDAO).viewAllProjectRequests();
    }


}
