package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.ManagerDAO;
import com.teamcore.manageapp.service.domain.Manager;
import com.teamcore.manageapp.service.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceImplTest {

    private ManagerDAO managerDAO;
    private ManagerServiceImpl managerService;


    @Before
    public void setUp() {
        managerDAO = Mockito.mock(ManagerDAO.class);
        managerService = new ManagerServiceImpl();
        managerService.setManagerDAO(managerDAO);
    }


    @Test
    public void getAll() throws Exception {
        List<Manager> testList =  testManagerList();

        //setup mock
        when(managerDAO.getAll()).thenReturn(testList);

        //invoke service api
        List<Manager> returnedUserList = managerService.getAll();

        assertEquals(testList.size(), returnedUserList.size());

        for (int i = 0; i < testList.size(); i++) {
            assertTrue(testList.get(i).equals(returnedUserList.get(i)));
        }

        verify(managerDAO, times(1)).getAll();
        verifyNoMoreInteractions(managerDAO);
    }

    @Test
    public void getById() throws Exception {
        Manager testUser = testManager();

        //setup mock
        when(managerDAO.getById(1L))
                .thenReturn(testUser);

        User returnedUser = managerService.getById(1L);

        assertTrue(returnedUser.equals(testUser));


        verify(managerDAO, times(1)).getById(any(Long.class));
        verifyNoMoreInteractions(managerDAO);

    }

    @Test
    public void save() throws Exception {
        Manager testUser = testManager();

        when(managerDAO.save(testUser)).thenReturn(testUser);

        User returnedUser = managerService.save(testUser);

        assertTrue(returnedUser.equals(testUser));

        verify(managerDAO, times(1)).save(any(Manager.class));
        verifyNoMoreInteractions(managerDAO);
    }


    @Test
    public void update() throws Exception {
        Manager testUser = testManager();

        when(managerDAO.update(testUser)).thenReturn(testUser);

        User returnedUser = managerService.update(testUser);

        assertTrue(returnedUser.equals(testUser));

        verify(managerDAO, times(1)).update(any(Manager.class));
        verifyNoMoreInteractions(managerDAO);
    }

    @Test
    public void delete() throws Exception {
        managerService.delete(1L);
        verify(managerDAO, times(1)).delete(any(Long.class));
        verifyNoMoreInteractions(managerDAO);
    }

    //utility methods
    private List<Manager> testManagerList() {
        List<Manager> userList = new ArrayList<>();
        Manager.Builder managerBuilder = Manager.newBuilder();
        for (long i = 0; i < 2; i++) {
            userList.add(managerBuilder.setId(i)
                    .setName("user"+i)
                    .setEmail("email"+i)
                    .build());
        }
        return userList;
    }

    private Manager testManager() {
        return Manager.newBuilder()
                .setId(1L)
                .setName("Peter")
                .setEmail("peter@epam.com")
                .build();
    }

}