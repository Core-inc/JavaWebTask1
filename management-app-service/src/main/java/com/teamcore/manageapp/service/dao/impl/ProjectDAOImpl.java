package com.teamcore.manageapp.service.dao.impl;


import com.teamcore.manageapp.service.dao.ProjectDAO;
import com.teamcore.manageapp.service.domain.Project;
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
import java.util.Date;
import java.util.List;

/**
 * ProjectDAO implementation
 */

@Repository
public class ProjectDAOImpl implements ProjectDAO {

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

    private static final String UPDATE_PROJECT ="UPDATE t_projects SET c_exter_name = :c_exter_name, c_inter_name = :c_inter_name, c_specs_link = :c_specs_link, c_status = :c_status, c_created_at = :c_created_at, c_updated_at = :c_updated_at WHERE id = :id";

    public int addNewProject(Project project) {

        /*
        SimpleDateFormat formatDate = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String formatted = formatDate.format(project.getCreatedAt());
        String formatted2 = formatDate.format(project.getUpdatedAt());
        */


        SqlParameterSource ps = new MapSqlParameterSource()
                // .addValue("id", project.getId())
                .addValue("c_exter_name", project.getExternalName())
                .addValue("c_inter_name", project.getInternalName())
                .addValue("c_specs_link", project.getSpecLink())
                .addValue("c_status", project.getStatus())
                .addValue("c_created_at", Timestamp.valueOf(project.getCreatedAt()))
                .addValue("c_updated_at", Timestamp.valueOf(project.getUpdatedAt()));

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int returnValue = namedParameterJdbcTemplate.update(ADD_PROJECT, ps, keyHolder,new String[]{"id"});


        if(1 == returnValue)
            System.out.println("Project creation is SUCCESS");
        else
            System.out.println("Project creation is FAILURE");

        return returnValue;
    }

    public List<Project> viewAllProjects() {
        List<Project> lstProjects;
        try {

           lstProjects = namedParameterJdbcTemplate.query(VIEW_ALL_PROJECTS, new ProjectRowMapper());

        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no projects in database");
                return null;
            }

        return lstProjects;
    }

    public Project findById(Long id) {
        Project project;

        try {
            SqlParameterSource ps = new MapSqlParameterSource("id", id);
            project = namedParameterJdbcTemplate.queryForObject(FIND_PROJECT_BY_ID, ps, new ProjectRowMapper());
        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no project with such id");
            return null;
        }

        return project;

    }

    public void deleteById(Long id) {

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


    }

    public Project findByInternalName(String internalName) {
        Project project;
        try{
            SqlParameterSource ps = new MapSqlParameterSource("c_inter_name", internalName);
            project = namedParameterJdbcTemplate.queryForObject(FIND_PROJECT_BY_INTERNALNAME,ps,new ProjectRowMapper());

        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no project with such internalName");
            return null;
        }
        return project;
    }

    public void deleteByInternalName(String internalName) {

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

    }

    public int updateProject(Project newProject) {
        SqlParameterSource ps = new MapSqlParameterSource()
                .addValue("id", newProject.getId())
                .addValue("c_exter_name", newProject.getExternalName())
                .addValue("c_inter_name", newProject.getInternalName())
                .addValue("c_specs_link", newProject.getSpecLink())
                .addValue("c_status", newProject.getStatus())
                .addValue("c_created_at", Timestamp.valueOf(newProject.getCreatedAt()))
                .addValue("c_updated_at", Timestamp.valueOf(newProject.getUpdatedAt()));

        int returnValue = namedParameterJdbcTemplate.update(UPDATE_PROJECT,ps);
        return returnValue;


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
