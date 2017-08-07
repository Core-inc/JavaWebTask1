package com.teamcore.manageapp.web.controllers.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;
import com.teamcore.manageapp.service.services.user.UserService;
import com.teamcore.manageapp.web.controllers.rest.config.TestConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UserControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private UserService userServiceMock;

    @InjectMocks
    private UserController userController;

    @BeforeClass
    public static void testSuiteSetup() {
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void listAllUsers() throws Exception {
        User firstUser = getFirstUser();
        User secondUser = new User.Builder()
                .id(2)
                .name("second")
                .email("second@epam.com")
                .password("654321")
                .salt("ewq")
                .createdAt(LocalDateTime.MAX)
                .updatedAt(LocalDateTime.MIN)
                .roleId(1)
                .build();

        Mockito.<List<?>>when(userServiceMock.listAll()).thenReturn(Arrays.asList(firstUser, secondUser));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("first")))
                .andExpect(jsonPath("$[0].email", is("first@epam.com")))
                .andExpect(jsonPath("$[0].password", is("123456")))
                .andExpect(jsonPath("$[0].salt", is("qwe")))
//                .andExpect(jsonPath("$[0].createdAt", is(LocalDateTime.MIN)))
//                .andExpect(jsonPath("$[0].updatedAt", is(LocalDateTime.MAX)))
                .andExpect(jsonPath("$[0].roleId", is(0)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("second")))
                .andExpect(jsonPath("$[1].email", is("second@epam.com")))
                .andExpect(jsonPath("$[1].password", is("654321")))
                .andExpect(jsonPath("$[1].salt", is("ewq")))
//                .andExpect(jsonPath("$[1].createdAt", is(LocalDateTime.MAX)))
//                .andExpect(jsonPath("$[1].updatedAt", is(LocalDateTime.MIN)))
                .andExpect(jsonPath("$[1].roleId", is(1)));

        verify(userServiceMock, times(1)).listAll();
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void userById_Found() throws Exception {
        User user = getFirstUser();

        when(userServiceMock.getById(1)).thenReturn(user);

        validateSuccessfulGetRequest("/users/1");

        verify(userServiceMock, times(1)).getById(1);
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void userByEmail_Found() throws Exception {
        User user = getFirstUser();

        when(userServiceMock.getByEmail(user.getEmail())).thenReturn(user);

        validateSuccessfulGetRequest("/users/email/first@epam.com");

        verify(userServiceMock, times(1)).getByEmail(user.getEmail());
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void allUsersByName_Found() throws Exception {
        User firstUser = getFirstUser();
        User secondUser = new User.Builder()
                .id(2)
                .name("first")
                .email("second@epam.com")
                .password("654321")
                .salt("ewq")
                .createdAt(LocalDateTime.MAX)
                .updatedAt(LocalDateTime.MIN)
                .roleId(1)
                .build();

        when(userServiceMock.getAllByName("first")).thenReturn(Arrays.asList(firstUser, secondUser));

        mockMvc.perform(get("/users/name/first"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("first")))
                .andExpect(jsonPath("$[0].email", is("first@epam.com")))
                .andExpect(jsonPath("$[0].password", is("123456")))
                .andExpect(jsonPath("$[0].salt", is("qwe")))
//                .andExpect(jsonPath("$[0].createdAt", is(LocalDateTime.MIN)))
//                .andExpect(jsonPath("$[0].updatedAt", is(LocalDateTime.MAX)))
                .andExpect(jsonPath("$[0].roleId", is(0)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("first")))
                .andExpect(jsonPath("$[1].email", is("second@epam.com")))
                .andExpect(jsonPath("$[1].password", is("654321")))
                .andExpect(jsonPath("$[1].salt", is("ewq")))
//                .andExpect(jsonPath("$[1].createdAt", is(LocalDateTime.MAX)))
//                .andExpect(jsonPath("$[1].updatedAt", is(LocalDateTime.MIN)))
                .andExpect(jsonPath("$[1].roleId", is(1)));

        verify(userServiceMock, times(1)).getAllByName("first");
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void roleByUserId_Found() throws Exception {
        Role role = new Role();
        role.setId(1);
        role.setRoleId(0);
        role.setName("admin");

        when(userServiceMock.getRoleByUserId(1)).thenReturn(role);

        mockMvc.perform(get("/1/role"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.roleId", is(0)))
                .andExpect(jsonPath("$.name", is("admin")));

        verify(userServiceMock, times(1)).getRoleByUserId(1);
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void saveUser() throws Exception {
        User user = getFirstUser();
        user.setId(null);

        User addedUser = getFirstUser();

        when(userServiceMock.saveOrUpdate(user)).thenReturn(addedUser);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsBytes(user))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("first")))
                .andExpect(jsonPath("$.email", is("first@epam.com")))
                .andExpect(jsonPath("$.password", is("123456")))
                .andExpect(jsonPath("$.salt", is("qwe")))
//                .andExpect(jsonPath("$.createdAt", is(LocalDateTime.MIN)))
//                .andExpect(jsonPath("$.updatedAt", is(LocalDateTime.MAX)))
                .andExpect(jsonPath("$.roleId", is(0)));

        verify(userServiceMock, times(1)).saveOrUpdate(user);
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void deleteUser() throws Exception {

    }

    @Test
    public void userById_NotFound() throws Exception {
        //TODO add real exception
//        when(userServiceMock.getById(1)).thenTrow();

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isNotFound());

        verify(userServiceMock, times(1)).getById(1);
        verifyNoMoreInteractions(userServiceMock);
    }

    private User getFirstUser() {
        return new User.Builder()
                .id(1)
                .name("first")
                .email("first@epam.com")
                .password("123456")
                .salt("qwe")
                .createdAt(LocalDateTime.MIN)
                .updatedAt(LocalDateTime.MAX)
                .roleId(0)
                .build();
    }

    private void validateSuccessfulGetRequest(String url) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("first")))
                .andExpect(jsonPath("$.email", is("first@epam.com")))
                .andExpect(jsonPath("$.password", is("123456")))
                .andExpect(jsonPath("$.salt", is("qwe")))
//                .andExpect(jsonPath("$.createdAt", is(LocalDateTime.MIN)))
//                .andExpect(jsonPath("$.updatedAt", is(LocalDateTime.MAX)))
                .andExpect(jsonPath("$.roleId", is(0)));
    }
}