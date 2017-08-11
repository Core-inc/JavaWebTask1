package com.teamcore.manageapp.service.dao.impl;

import com.teamcore.manageapp.service.dao.TaskDAO;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of TaskDAO interface
 */

@Repository
public class TaskDAOImpl implements TaskDAO {

    //private final static Logger logger = LoggerFactory.getLogger(TaskDAOImpl.class);
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String FIND_TASK_BY_ID = "SELECT * FROM t_tasks WHERE id = :id";
    private final static String DELETE_TASK_BY_ID = "DELETE FROM t_tasks WHERE id = :id";
    private final static String UPDATE_TASK = "UPDATE t_tasks SET c_name = :c_name, c_cost = :c_cost, c_duration = :c_duration, c_status = :c_status, " +
            "c_created_at = :c_created_at, c_updated_at = :c_updated_at, c_project_id = :c_project_id WHERE id = :id";
    private final static String INSERT_TASK = "INSERT INTO t_tasks (c_name, c_cost, c_duration, c_status, c_created_at, c_updated_at, c_project_id) " +
            "VALUES (:c_name, :c_cost, :c_duration, :c_status, :c_created_at, :c_updated_at, :c_project_id)";
    private final static String FIND_TASKS_BY_PROJECT_ID = "SELECT * FROM t_tasks WHERE c_project_id = :c_project_id";

    //    private final static String FIND_DEVELOPERS_BY_TASK_ID = "SELECT c_developer_id FROM t_developers_tasks WHERE c_task_id = :c_task_id";
    private final static  String FIND_DEVELOPERS_BY_TASK_ID = "WITH developer_ids AS "+
            "(SELECT c_developer_id FROM t_developers_tasks WHERE c_task_id = :c_task_id) " +
            "SELECT * FROM t_developers JOIN developer_ids ON (t_developers.c_user_id = developer_ids.c_developer_id)";

    @Override
    public Task findTaskById(long id) {
        //logger.debug("Find task (id={})", id);

        return jdbcTemplate.queryForObject(FIND_TASK_BY_ID,
                new MapSqlParameterSource("id", id),
                TaskDAOImpl::taskRowMap);

//        String loggerMessage = (task == null)?("Task #"+id +"found."):("Task #"+id +"not found.");
//        logger.info(loggerMessage);
    }

    @Override
    public void deleteTaskById(long id) {
        //logger.debug("Delete task (id={})", id);

        jdbcTemplate.update(DELETE_TASK_BY_ID,
                new MapSqlParameterSource("id", id));

//        String loggerMessage = result==1?("Task #" + id + " delete is SUCCESSFUL."):("Task #" + id + " delete is FAILURE.");
//        logger.debug(loggerMessage);
    }

    @Override
    public void updateTask(Task task) {
        //logger.debug("Update task (id={})", task.getId());

        jdbcTemplate.update(UPDATE_TASK,
                new MapSqlParameterSource()
                        .addValue("id", task.getId())
                        .addValue("c_name", task.getName())
                        .addValue("c_cost", task.getCost())
                        .addValue("c_duration", task.getDuration())
                        .addValue("c_status", task.getStatus())
                        .addValue("c_created_at", Timestamp.valueOf(task.getCreatedAt()))
                        .addValue("c_updated_at", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("c_project_id", task.getProjectId())
        );

//        String loggerMessage = result==1?("Task #" + task.getId() + "update is SUCCESSFUL."):("Task #" + task.getId() + "update is FAILURE.");
//        logger.debug(loggerMessage);
    }

    @Override
    public Task addTask(Task task) {
        //logger.debug("Add task");

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(INSERT_TASK,
                new MapSqlParameterSource()
                        .addValue("c_name", task.getName())
                        .addValue("c_cost", task.getCost())
                        .addValue("c_duration", task.getDuration())
                        .addValue("c_status", task.getStatus())
                        .addValue("c_created_at", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("c_updated_at", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("c_project_id", task.getProjectId()),
                keyHolder, new String[]{"id"});

        task.setId(keyHolder.getKey().longValue());

//        String loggerMessage = result==1?("Task #" + task.getId() + "insert is SUCCESSFUL."):("Task #" + task.getId()  + "insert is FAILURE.");
//        logger.debug(loggerMessage);

        return task;
    }

    @Override
    public List<Task> findAllTasksByProject(Project project) {
        //logger.debug("findAllByProject (project id={})", project.getId());

        List<Task> taskList = jdbcTemplate.query(FIND_TASKS_BY_PROJECT_ID,
                new MapSqlParameterSource("c_project_id", project.getId()),
                TaskDAOImpl::taskRowMap);

//        String loggerMesage = taskList.isEmpty()?("Project #"+project.getId() +"hasn't tasks."):("Task #"+project.getId() +" has "+taskList.size()+" tasks.");
//        logger.debug(loggerMesage);

        return taskList;
    }

//    public List<Developer> findAllDevelopersIdByTask(Task task) {
//        SqlParameterSource source = new MapSqlParameterSource()
//                .addValue("c_task_id", task.getId());
//        namedParameterJdbcTemplate.queryForObject(FIND_DEVELOPERS_BY_TASK_ID, source, new RowMap)
//
//    }

    private static Task taskRowMap(ResultSet resultSet, int rowNumber) throws SQLException {
        return Task.newBuilder()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("c_name"))
                .setCost(resultSet.getLong("c_cost"))
                .setDuration(resultSet.getLong("c_duration"))
                .setStatus(resultSet.getInt("c_status"))
                .setCreatedAt(resultSet.getTimestamp("c_created_at").toLocalDateTime())
                .setUpdatedAt(resultSet.getTimestamp("c_updated_at").toLocalDateTime())
                .setProjectId(resultSet.getLong("c_project_id"))
                .build();
    }
}
