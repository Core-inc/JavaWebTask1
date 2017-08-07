package com.epam.managementapp.service.dao.project.impl;


import com.epam.managementapp.service.dao.project.ProjectDAO;
import com.epam.managementapp.service.dao.project.utils.ProjectRowMapper;
import com.epam.managementapp.service.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

/**
 * ProjectDAO implementation
 */
//@Service
@Repository
public class ProjectDAOImpl implements ProjectDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired
    public void getNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        // this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    private static final String ADD_PROJECT = "INSERT INTO PROJECTS (outername, innername, techfile, status, "+
    "createdat, updatedat) VALUES(:outername, :innername, :techfile, :status, :createdat, "+
    ":updatedat)";

    private static final String VIEW_ALL_PROJECTS = "SELECT id, outername, innername, techfile, status, createdat, updatedat FROM PROJECTS";

    private static final String FIND_PROJECT_BY_ID = "SELECT id, outername, innername, techfile, status, createdat, updatedat FROM PROJECTS WHERE id = :id";

    private static final String DELETE_PROJECT_BY_ID ="DELETE FROM PROJECTS WHERE id =:id ";

    private static final String FIND_PROJECT_BY_INNERNAME = "SELECT id, outername, innername, techfile, status, createdat, updatedat FROM PROJECTS WHERE innername = :innername";

    private static final String DELETE_PROJECT_BY_INNERNAME ="DELETE FROM PROJECTS WHERE innername =:innername ";

    private static final String UPDATE_PROJECT ="UPDATE PROJECTS SET outername = :outername, innername = :innername, techfile = :techfile, status = :status, createdat = :createdat, updatedat = :updatedat WHERE id = :id";

    public int addNewProject(Project project) {

        SqlParameterSource ps = new MapSqlParameterSource()
               // .addValue("id", project.getId())
                .addValue("outername", project.getOuterName())
                .addValue("innername", project.getInnerName())
                .addValue("techfile", project.getTechFile())
                .addValue("status", project.getStatus())
                .addValue("createdat", project.getCreatedAt())
                .addValue("updatedat", project.getUpdatedAt());

            KeyHolder keyHolder = new GeneratedKeyHolder();

        int returnValue = namedParameterJdbcTemplate.update(ADD_PROJECT, ps, keyHolder,new String[]{"id"});


        if(1 == returnValue)
            System.out.println("Project creation is SUCCESS");
        else
            System.out.println("Project creation is FAILURE");

        return returnValue;
    }

    public List<Project> viewAllProjects() {

        List<Project> lstProjects  = namedParameterJdbcTemplate.query(
                VIEW_ALL_PROJECTS,
                new ProjectRowMapper());
        return lstProjects;
    }

    public Project findById(int id) {
        Project project;

        try {
            SqlParameterSource ps = new MapSqlParameterSource("id", id);
            project = namedParameterJdbcTemplate.queryForObject(FIND_PROJECT_BY_ID, ps, new ProjectRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        return project;

    }

    public String deleteById(int id) {

        SqlParameterSource ps = new MapSqlParameterSource("id", id);

        int returnValue = namedParameterJdbcTemplate.update(DELETE_PROJECT_BY_ID,ps);

        if(1 == returnValue)
            return "Project deletion is SUCCESS";
        else
            return "Project deletion is FAILURE";

    }

    public Project findByInnerName(String innerName) {
        Project project;
        try{
        SqlParameterSource ps = new MapSqlParameterSource("innername", innerName);
        project = namedParameterJdbcTemplate.queryForObject(FIND_PROJECT_BY_INNERNAME,ps,new ProjectRowMapper());

        } catch (EmptyResultDataAccessException e) {
          return null;
        }
        return project;
    }

    public String deleteByInnerName(String innerName) {
        SqlParameterSource ps = new MapSqlParameterSource("innername", innerName);

        int returnValue = namedParameterJdbcTemplate.update(DELETE_PROJECT_BY_INNERNAME,ps);

        if(1 == returnValue)
            return "Project deletion is SUCCESS";
        else
            return "Project deletion is FAILURE";

    }

    public String updateProject(Project newProject) {
        SqlParameterSource ps = new MapSqlParameterSource()
                .addValue("id", newProject.getId())
                .addValue("outername", newProject.getOuterName())
                .addValue("innername", newProject.getInnerName())
                .addValue("techfile", newProject.getTechFile())
                .addValue("status", newProject.getStatus())
                .addValue("createdat", newProject.getCreatedAt())
                .addValue("updatedat", newProject.getUpdatedAt());

        int returnValue = namedParameterJdbcTemplate.update(UPDATE_PROJECT,ps);

        if(1 == returnValue)
            return "Project updation is SUCCESS";
        else
            return "Project updation is FAILURE";

    }

    public List<Project> findAfterTheDate(Date date) {
        return null;
    }
}
