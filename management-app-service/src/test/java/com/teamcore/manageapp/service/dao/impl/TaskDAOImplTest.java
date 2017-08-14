package com.teamcore.manageapp.service.dao.impl;

import com.teamcore.manageapp.service.config.TestServiceConfig;
import com.teamcore.manageapp.service.dao.DeveloperDAO;
import com.teamcore.manageapp.service.dao.TaskDAO;
import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Task;
import com.teamcore.manageapp.service.utils.TestFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestServiceConfig.class})
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                scripts = {"classpath:db/init_schema.sql", "classpath:db/init_data.sql"}),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                scripts = {"classpath:db/cleanup.sql"})
})
public class TaskDAOImplTest {

    private TaskDAO taskDAO;
    private DeveloperDAO developerDAO;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Autowired
    private void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Autowired
    private void setDeveloperDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    @Test
    public void addTask() {

        Task task = TestFactory.createNewTask();

        Task dbTask = taskDAO.addTask(task);

        assertNotNull(dbTask);

        //check that task was inserted in db
        Task returnedTask = taskDAO.findTaskById(dbTask.getId());

        assertEquals(task.getName(), returnedTask.getName());
        assertEquals(task.getCost(), returnedTask.getCost());
        assertEquals(task.getStatus(), returnedTask.getStatus());
        assertEquals(task.getDuration(), returnedTask.getDuration());
        assertEquals(task.getProjectId(), returnedTask.getProjectId());
    }


    @Test
    public void addDeveloperToTask() {

        Task task = taskDAO.findTaskById(1L);
        System.out.println("Task: "+task);
        //  Developer developer = TestFactory.createDefaultNewDeveloper();
        Developer developer = developerDAO.getById(3L);
        System.out.println("Developer: "+developer);
        taskDAO.addDeveloperToTask(developer,task);

        List<Developer> list = taskDAO.getDeveloperByTask(task);
        System.out.println("Developer: "+list.get(0));

        assertEquals(list.get(0), developer);

    }

    @Test
    public void deleteTask() {

        Integer rowCount = JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_tasks");

        taskDAO.deleteTaskById(1L);

        assertEquals(rowCount - 1,
                JdbcTestUtils.countRowsInTable(jdbcTemplate, "t_tasks"));
    }

    @Test
    public void updateTask() {

        Task task = TestFactory.createExistedTask();

        String newName = "unit_testing";
        Integer newStatus = 2;

        //update name and status
        task.setName(newName);
        task.setStatus(newStatus);

        taskDAO.updateTask(task);

        //check that db updated
        Task returnedTask = taskDAO.findTaskById(task.getId());

        assertEquals(newName, returnedTask.getName());
        assertEquals(newStatus, returnedTask.getStatus());

    }

    @Test
    public void findTask() {

        Task task = TestFactory.createExistedTask();

        Task returnedTask = taskDAO.findTaskById(task.getId());

        assertEquals(task.getId(), returnedTask.getId());
        assertEquals(task.getName(), returnedTask.getName());
        assertEquals(task.getProjectId(), returnedTask.getProjectId());
    }

//    @Test
//    public void findAllTasksByProject() {
//
//       Task task = TestFactory.createExistedTask();
//
//
//        List<Task> dbTaskList = taskDAO.findAllTasksByProject(project);
//        for (Task cur : dbTaskList) {
//            assertTrue(cur.getName().equals("testing") || cur.getName().equals("testing2"));
//        }
//
//    }
}
