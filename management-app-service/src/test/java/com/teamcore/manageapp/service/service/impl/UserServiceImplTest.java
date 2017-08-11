package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.UserDAO;
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
public class UserServiceImplTest {

    private UserDAO userDAO;
    private UserServiceImpl userService;


    @Before
    public void setUp() {
        userDAO = Mockito.mock(UserDAO.class);
        userService = new UserServiceImpl();
        userService.setUserDAO(userDAO);
    }


    @Test
    public void getAll() throws Exception {
        List<User> testList =  testUserList();

        //setup mock
        when(userDAO.getAll()).thenReturn(testList);

        //invoke service api
        List<User> returnedUserList = userService.getAll();

        assertEquals(testList.size(), returnedUserList.size());

        for (int i = 0; i < testList.size(); i++) {
           assertTrue(testList.get(i).equals(returnedUserList.get(i)));
        }

        verify(userDAO, times(1)).getAll();
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void getById() throws Exception {
        User testUser = testUser();

        //setup mock
        when(userDAO.getById(1L))
                .thenReturn(testUser);

        User returnedUser = userService.getById(1L);

        assertTrue(returnedUser.equals(testUser));


        verify(userDAO, times(1)).getById(any(Long.class));
        verifyNoMoreInteractions(userDAO);

    }

    @Test
    public void save() throws Exception {
        User testUser = testUser();

        when(userDAO.save(testUser)).thenReturn(testUser);

        User returnedUser = userService.save(testUser);

        assertTrue(returnedUser.equals(testUser));

        verify(userDAO, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void update() throws Exception {
        User testUser = testUser();

        when(userDAO.update(testUser)).thenReturn(testUser);

        User returnedUser = userService.update(testUser);

        assertTrue(returnedUser.equals(testUser));

        verify(userDAO, times(1)).update(any(User.class));
        verifyNoMoreInteractions(userDAO);
    }

    @Test
    public void delete() throws Exception {
        userService.delete(1L);
        verify(userDAO, times(1)).delete(any(Long.class));
        verifyNoMoreInteractions(userDAO);
    }

    //utility methods
    private List<User> testUserList() {
        List<User> userList = new ArrayList<>();
        User.Builder userBuilder = User.newBuilder();
        for (long i = 0; i < 2; i++) {
            userList.add(userBuilder.setId(i)
                    .setName("user"+i)
                    .setEmail("email"+i)
                    .build());
        }
        return userList;
    }

    private User testUser() {
        return User.newBuilder()
                .setId(1L)
                .setName("Peter")
                .setEmail("peter@epam.com")
                .build();
    }

}