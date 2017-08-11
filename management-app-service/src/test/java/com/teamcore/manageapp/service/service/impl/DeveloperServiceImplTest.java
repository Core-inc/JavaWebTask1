package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.DeveloperDAO;
import com.teamcore.manageapp.service.domain.Developer;
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
public class DeveloperServiceImplTest {

    private DeveloperDAO developerDAO;
    private DeveloperServiceImpl developerService;


    @Before
    public void setUp() {
        developerDAO = Mockito.mock(DeveloperDAO.class);
        developerService = new DeveloperServiceImpl();
        developerService.setDeveloperDAO(developerDAO);
    }


    @Test
    public void getAll() throws Exception {
        List<Developer> testList =  testDeveloperList();

        //setup mock
        when(developerDAO.getAll()).thenReturn(testList);

        //invoke service api
        List<Developer> returnedUserList = developerService.getAll();

        assertEquals(testList.size(), returnedUserList.size());

        for (int i = 0; i < testList.size(); i++) {
            assertTrue(testList.get(i).equals(returnedUserList.get(i)));
        }

        verify(developerDAO, times(1)).getAll();
        verifyNoMoreInteractions(developerDAO);
    }

    @Test
    public void getById() throws Exception {
        Developer testUser = testDeveloper();

        //setup mock
        when(developerDAO.getById(1L))
                .thenReturn(testUser);

        Developer returnedUser = developerService.getById(1L);

        assertTrue(returnedUser.equals(testUser));


        verify(developerDAO, times(1)).getById(any(Long.class));
        verifyNoMoreInteractions(developerDAO);

    }

    @Test
    public void save() throws Exception {
        Developer testUser = testDeveloper();

        when(developerDAO.save(testUser)).thenReturn(testUser);

        User returnedUser = developerService.save(testUser);

        assertTrue(returnedUser.equals(testUser));

        verify(developerDAO, times(1)).save(any(Developer.class));
        verifyNoMoreInteractions(developerDAO);
    }

    @Test
    public void update() throws Exception {
        Developer testUser = testDeveloper();

        when(developerDAO.update(testUser)).thenReturn(testUser);

        User returnedUser = developerService.update(testUser);

        assertTrue(returnedUser.equals(testUser));

        verify(developerDAO, times(1)).update(any(Developer.class));
        verifyNoMoreInteractions(developerDAO);
    }

    @Test
    public void delete() throws Exception {
        developerService.delete(1L);
        verify(developerDAO, times(1)).delete(any(Long.class));
        verifyNoMoreInteractions(developerDAO);
    }

    //utility methods
    private List<Developer> testDeveloperList() {
        List<Developer> developerList = new ArrayList<>();
        Developer.Builder developerBuilder = Developer.newBuilder();
        for (long i = 0; i < 2; i++) {
            developerList.add(developerBuilder.setId(i)
                    .setName("user"+i)
                    .setEmail("email"+i)
                    .build());
        }
        return developerList;
    }

    private Developer testDeveloper() {
        return Developer.newBuilder()
                .setId(1L)
                .setName("Peter")
                .setEmail("peter@epam.com")
                .build();
    }

}