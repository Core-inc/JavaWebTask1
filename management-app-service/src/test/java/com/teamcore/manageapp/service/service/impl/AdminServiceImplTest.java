package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.AdminDAO;
import com.teamcore.manageapp.service.domain.Admin;
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
public class AdminServiceImplTest {

    private AdminDAO adminDAO;
    private AdminServiceImpl adminService;


    @Before
    public void setUp() {
        adminDAO = Mockito.mock(AdminDAO.class);
        adminService = new AdminServiceImpl();
        adminService.setAdminDAO(adminDAO);
    }


    @Test
    public void getAll() throws Exception {
        List<Admin> testList =  testAdminList();

        //setup mock
        when(adminDAO.getAll()).thenReturn(testList);

        //invoke service api
        List<Admin> returnedUserList = adminService.getAll();

        assertEquals(testList.size(), returnedUserList.size());

        for (int i = 0; i < testList.size(); i++) {
            assertTrue(testList.get(i).equals(returnedUserList.get(i)));
        }

        verify(adminDAO, times(1)).getAll();
        verifyNoMoreInteractions(adminDAO);
    }

    @Test
    public void getById() throws Exception {
        Admin testUser = testAdmin();

        //setup mock
        when(adminDAO.getById(1L))
                .thenReturn(testUser);

        User returnedUser = adminService.getById(1L);

        assertTrue(returnedUser.equals(testUser));


        verify(adminDAO, times(1)).getById(any(Long.class));
        verifyNoMoreInteractions(adminDAO);

    }

    @Test
    public void save() throws Exception {
        Admin testUser = testAdmin();

        when(adminDAO.save(testUser)).thenReturn(testUser);

        Admin returnedUser = adminService.save(testUser);

        assertTrue(returnedUser.equals(testUser));

        verify(adminDAO, times(1)).save(any(Admin.class));
        verifyNoMoreInteractions(adminDAO);
    }

    @Test
    public void update() throws Exception {
        Admin testUser = testAdmin();

        when(adminDAO.update(testUser)).thenReturn(testUser);

        Admin returnedUser = adminDAO.update(testUser);

        assertTrue(returnedUser.equals(testUser));

        verify(adminDAO, times(1)).update(any(Admin.class));
        verifyNoMoreInteractions(adminDAO);
    }

    @Test
    public void delete() throws Exception {
        adminService.delete(1L);
        verify(adminDAO, times(1)).delete(any(Long.class));
        verifyNoMoreInteractions(adminDAO);
    }

    //utility methods
    private List<Admin> testAdminList() {
        List<Admin> userList = new ArrayList<>();
        Admin.Builder adminBuilder = Admin.newBuilder();
        for (long i = 0; i < 2; i++) {
            userList.add(adminBuilder.setId(i)
                    .setName("user"+i)
                    .setEmail("email"+i)
                    .build());
        }
        return userList;
    }

    private Admin testAdmin() {
        return Admin.newBuilder()
                .setId(1L)
                .setName("Peter")
                .setEmail("peter@epam.com")
                .build();
    }

}