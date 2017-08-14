package com.teamcore.manageapp.service.dao.impl;


import com.teamcore.manageapp.service.dao.ProjectDAO;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * ProjectDAO implementation
 */

@Repository
public class ProjectDAOImpl implements ProjectDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectDAOImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired
    public void getNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    private static final String ADD_PROJECT = "INSERT INTO t_projects (c_exter_name, c_inter_name, c_specs_link, c_status, "+
            "c_created_at, c_updated_at) VALUES(:c_exter_name, :c_inter_name, :c_specs_link, :c_status, :c_created_at, "+
            ":c_updated_at)";

    private static final String VIEW_ALL_PROJECTS = "SELECT id, c_exter_name, c_inter_name, c_specs_link, c_status, c_created_at, c_updated_at FROM t_projects";

    private static final String FIND_PROJECT_BY_ID = "SELECT id, c_exter_name, c_inter_name, c_specs_link, c_status, c_created_at, c_updated_at FROM t_projects WHERE id = :id";

    private static final String DELETE_PROJECT_BY_ID ="DELETE FROM t_projects WHERE id =:id ";

    private static final String FIND_PROJECT_BY_INTERNALNAME = "SELECT id, c_exter_name, c_inter_name, c_specs_link, c_status, c_created_at, c_updated_at FROM t_projects WHERE c_inter_name = :c_inter_name";

    private static final String DELETE_PROJECT_BY_INTERNALNAME ="DELETE FROM t_projects WHERE c_inter_name =:c_inter_name ";

    private static final String UPDATE_PROJECT = "UPDATE t_projects SET c_exter_name = :c_exter_name, c_inter_name = :c_inter_name, c_specs_link = :c_specs_link, c_status = :c_status, c_created_at = :c_created_at, c_updated_at = :c_updated_at WHERE id = :id";

    private static final String FIND_PROJECT_BY_STATUS = "SELECT FROM t_projects WHERE c_status = :status";

    private static final String GET_PROJECT_CUSTOMER = "SELECT t_users.id as user_id, t_users.c_name as user_name, c_email, " +
            "c_password, c_salt, c_created_at, c_updated_at,  " +
            "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
            "FROM t_users JOIN t_customers_projects on t_users.id = c_customer_id JOIN t_user_groups on c_user_group_id = c_group_id " +
            "WHERE c_project_id = :projectId";

    public Project addNewProject(Project project) {
        LOGGER.debug("Adding new project: Project = {}", project);

        SqlParameterSource ps = new MapSqlParameterSource()
                // .addValue("id", project.getId())
                .addValue("c_exter_name", project.getExternalName())
                .addValue("c_inter_name", project.getInternalName())
                .addValue("c_specs_link", project.getSpecLink())
                .addValue("c_status", project.getStatus())
                .addValue("c_created_at", Timestamp.valueOf(LocalDateTime.now()))
                .addValue("c_updated_at", Timestamp.valueOf(LocalDateTime.now()));

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int returnValue = namedParameterJdbcTemplate.update(ADD_PROJECT, ps, keyHolder,new String[]{"id"});
        project.setId(keyHolder.getKey().longValue());

        LOGGER.debug("Adding is finished. Project from db = {}: ", project);

        return project;
    }

    public List<Project> viewAllProjects() {
        LOGGER.debug("Starting to view all projects: ");
        List<Project> lstProjects;
        try {

            lstProjects = namedParameterJdbcTemplate.query(VIEW_ALL_PROJECTS, new ProjectRowMapper());

        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no projects in database");
            return null;
        }

        LOGGER.debug("Listing prijects is finished. Projects: {}", lstProjects);
        return lstProjects;
    }

    public Project findById(Long id) {
        LOGGER.debug("Starting to find project: Params id = {}", id);
        Project project;

        try {
            SqlParameterSource ps = new MapSqlParameterSource("id", id);
            project = namedParameterJdbcTemplate.queryForObject(FIND_PROJECT_BY_ID, ps, new ProjectRowMapper());
        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no project with such id");
            return null;
        }

        LOGGER.debug("Project was found. Project = {} ", project);
        return project;

    }

    public void deleteById(Long id) {
        LOGGER.debug("Starting to delete project: Params id = {}", id);

        int returnValue;
        try {
            SqlParameterSource ps = new MapSqlParameterSource("id", id);
            returnValue = namedParameterJdbcTemplate.update(DELETE_PROJECT_BY_ID, ps);
            if(1 == returnValue)
                System.out.println("Project deletion is SUCCESS");
            else
                System.out.println("Project deletion is FAILURE");
        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no project with such id");
        }

        LOGGER.debug("Project was deleted: Params id= {}", id);

    }

    public Project findByInternalName(String internalName) {
        LOGGER.debug("Starting to find project: Params internalName = {}", internalName);
        Project project;
        try{
            SqlParameterSource ps = new MapSqlParameterSource("c_inter_name", internalName);
            project = namedParameterJdbcTemplate.queryForObject(FIND_PROJECT_BY_INTERNALNAME,ps,new ProjectRowMapper());

        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no project with such internalName");
            return null;
        }
        LOGGER.debug("Project was found: Params internalName= {}", internalName);
        return project;
    }

    public void deleteByInternalName(String internalName) {
        LOGGER.debug("Starting to delete project: Params innername = {}", internalName);

        int returnValue;
        try {
            SqlParameterSource ps = new MapSqlParameterSource("c_inter_name", internalName);

            returnValue = namedParameterJdbcTemplate.update(DELETE_PROJECT_BY_INTERNALNAME, ps);
            if(1 == returnValue)
                System.out.println("Project deletion is SUCCESS");
            else
                System.out.println("Project deletion is FAILURE");
        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no project with such internalName");
        }
        LOGGER.debug("Project was deleted: Params innername= {}", internalName);

    }

    public Project updateProject(Project newProject) {
        LOGGER.debug("Starting to update project: New project = {}", newProject);
        SqlParameterSource ps = new MapSqlParameterSource()
                .addValue("id", newProject.getId())
                .addValue("c_exter_name", newProject.getExternalName())
                .addValue("c_inter_name", newProject.getInternalName())
                .addValue("c_specs_link", newProject.getSpecLink())
                .addValue("c_status", newProject.getStatus())
                .addValue("c_created_at", Timestamp.valueOf(newProject.getCreatedAt()))
                .addValue("c_updated_at", Timestamp.valueOf(newProject.getUpdatedAt()));

        int returnValue = namedParameterJdbcTemplate.update(UPDATE_PROJECT,ps);
        LOGGER.debug("Project was updated: New project= {}", newProject);
        return newProject;


    }

    @Override
    public List<Project> getByStatus(int status) {
        SqlParameterSource ps = new MapSqlParameterSource()
                .addValue("status", status);

        return namedParameterJdbcTemplate.query(FIND_PROJECT_BY_STATUS, new ProjectRowMapper());
    }

    public User getProjectCustomer(Long id) {
        return namedParameterJdbcTemplate.queryForObject(GET_PROJECT_CUSTOMER,
                new MapSqlParameterSource()
                        .addValue("projectId", id), UserDAOImpl::userRowMap);
    }

    public static class ProjectRowMapper implements RowMapper<Project> {

        public Project mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            Project project = Project.getProject();
            project.setId(resultSet.getLong("id"));
            project.setExternalName(resultSet.getString("c_exter_name"));
            project.setInternalName(resultSet.getString("c_inter_name"));
            project.setSpecLink(resultSet.getString("c_specs_link"));
            project.setStatus(resultSet.getInt("c_status"));
            project.setCreatedAt(resultSet.getTimestamp("c_created_at").toLocalDateTime());
            project.setUpdatedAt(resultSet.getTimestamp("c_updated_at").toLocalDateTime());
            return project;
        }
    }

}
