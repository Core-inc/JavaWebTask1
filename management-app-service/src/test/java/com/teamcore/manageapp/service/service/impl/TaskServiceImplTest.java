package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.DeveloperDAO;
import com.teamcore.manageapp.service.dao.TaskDAO;
import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Task;
import com.teamcore.manageapp.service.utils.TestFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {


    @Mock
    private TaskDAO taskDAO;

    @Mock
    private DeveloperDAO developerDAO;

    @InjectMocks
    private TaskServiceImpl taskService;


    @Test
    public void addTask() {

        Task mocTask = TestFactory.createNewTask();
        Task testTask = TestFactory.createNewTask();

        when(taskDAO.addTask(testTask)).thenReturn(mocTask);

        testTask = taskService.addTask(testTask);

        assertEquals(mocTask, testTask);
        verify(taskDAO, times(1)).addTask(any(Task.class));
        verifyNoMoreInteractions(taskDAO);
    }

    @Test
    public void deleteTask() {
        taskService.delete(1L);
        verify(taskDAO, times(1)).deleteTaskById(any(Long.class));
        verifyNoMoreInteractions(taskDAO);
    }

    @Test
    public void updateTask() {

        Task testTask = TestFactory.createNewTask();
        taskService.updateTask(testTask);

        verify(taskDAO, times(1)).updateTask(testTask);
        verifyNoMoreInteractions(taskDAO);

    }

    @Test
    public void findTask() {

        Task mocTask = TestFactory.createNewTask();
        mocTask.setId(1L);


        when(taskDAO.findTaskById(1)).thenReturn(mocTask);
        Task returnedTask = taskService.findTaskById(1);

        assertEquals(mocTask, returnedTask);
        verify(taskDAO, times(1)).findTaskById(any(Long.class));
        verifyNoMoreInteractions(taskDAO);

    }
    @Test
    public void addDeveloperToTask() {
        Task mocTask = TestFactory.createNewTask();
        mocTask.setId(1L);
        System.out.println("Task: "+mocTask);
        Developer developer = TestFactory.createDefaultNewDeveloper();
        developer.setId(5L);
        System.out.println("Developer: "+developer);

        taskService.addDeveloperToTask(developer, mocTask);
        verify(taskDAO, times(1)).addDeveloperToTask(developer,mocTask);
        verifyNoMoreInteractions(taskDAO);
    }

}