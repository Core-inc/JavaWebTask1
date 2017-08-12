package com.teamcore.manageapp.service.dao.impl;


import com.teamcore.manageapp.service.dao.ProjectRequestDAO;
import com.teamcore.manageapp.service.domain.ProjectRequest;
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
import java.util.Date;
import java.util.List;

/**
 * ProjectDAO implementation
 */

@Repository
public class ProjectRequestDAOImpl implements ProjectRequestDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectRequestDAOImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired
    public void getNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    private static final String ADD_PROJECT_REQUEST = "INSERT INTO t_project_requests (c_exter_name, c_specs_link, c_customer_name, c_customer_email) VALUES(:c_exter_name, :c_specs_link, :c_customer_name, :c_customer_email)";

    private static final String VIEW_ALL_PROJECT_REQUESTS = "SELECT id, c_exter_name, c_specs_link, c_customer_name, c_customer_email FROM t_project_requests";

    private static final String FIND_PROJECT_REQUEST_BY_ID = "SELECT id, c_exter_name, c_specs_link, c_customer_name, c_customer_email FROM t_project_requests WHERE id = :id";

    private static final String DELETE_PROJECT_REQUEST_BY_ID ="DELETE FROM t_project_requests WHERE id =:id ";

    private static final String FIND_PROJECT_REQUEST_BY_CUSTOMERNAME = "SELECT id, c_exter_name, c_specs_link, c_customer_name, c_customer_email FROM t_project_requests WHERE c_customer_name = :c_customer_name";

    private static final String UPDATE_PROJECT_REQUEST ="UPDATE t_project_requests SET c_exter_name = :c_exter_name, c_specs_link = :c_specs_link, c_customer_name = :c_customer_name, c_customer_email = :c_customer_email WHERE id = :id";

    public ProjectRequest addNewProjectRequest(ProjectRequest projectRequest) {
        LOGGER.debug("Adding new project request: ProjectRequest = {}", projectRequest);

        SqlParameterSource ps = new MapSqlParameterSource()
                .addValue("c_exter_name", projectRequest.getExternalName())
                .addValue("c_specs_link", projectRequest.getSpecLink())
                .addValue("c_customer_name", projectRequest.getCustomerName())
                .addValue("c_customer_email", projectRequest.getCustomerEmail());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int returnValue = namedParameterJdbcTemplate.update(ADD_PROJECT_REQUEST, ps, keyHolder,new String[]{"id"});
        projectRequest.setId(keyHolder.getKey().longValue());

        LOGGER.debug("Adding is finished. Project from db = {}: ", projectRequest);

        return projectRequest;
    }

    public List<ProjectRequest> viewAllProjectRequests() {
        LOGGER.debug("Starting to view all project requests: ");
        List<ProjectRequest> lstProjectRequests;
        try {

            lstProjectRequests = namedParameterJdbcTemplate.query(VIEW_ALL_PROJECT_REQUESTS, new ProjectRequestRowMapper());

        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no project requests in database");
            return null;
        }

        LOGGER.debug("Listing priject requests is finished. ProjectRequests: {}", lstProjectRequests);
        return lstProjectRequests;
    }

    public ProjectRequest findById(Long id) {
        LOGGER.debug("Starting to find project request: Params id = {}", id);
        ProjectRequest projectRequest;

        try {
            SqlParameterSource ps = new MapSqlParameterSource("id", id);
            projectRequest = namedParameterJdbcTemplate.queryForObject(FIND_PROJECT_REQUEST_BY_ID, ps, new ProjectRequestRowMapper());
        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no project request with such id");
            return null;
        }

        LOGGER.debug("Project request was found. ProjectRequest = {} ", projectRequest);
        return projectRequest;

    }

    public void deleteById(Long id) {
        LOGGER.debug("Starting to delete project request: Params id = {}", id);

        int returnValue;
        try {
            SqlParameterSource ps = new MapSqlParameterSource("id", id);
            returnValue = namedParameterJdbcTemplate.update(DELETE_PROJECT_REQUEST_BY_ID, ps);
            if(1 == returnValue)
                System.out.println("Project request deletion is SUCCESS");
            else
                System.out.println("Project request deletion is FAILURE");
        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no project request with such id");
        }

        LOGGER.debug("Project request was deleted: Params id= {}", id);

    }

    public ProjectRequest findByCustomerName(String customerName) {
        LOGGER.debug("Starting to find project request: Params customerName = {}", customerName);
        ProjectRequest projectRequest;
        try{
            SqlParameterSource ps = new MapSqlParameterSource("c_customer_name", customerName);
            projectRequest = namedParameterJdbcTemplate.queryForObject(FIND_PROJECT_REQUEST_BY_CUSTOMERNAME,ps,new ProjectRequestRowMapper());

        } catch (EmptyResultDataAccessException e) {
            System.out.println("There are no project request with such customerName");
            return null;
        }
        LOGGER.debug("Project request was found: Params customerName= {}", customerName);
        return projectRequest;
    }

    public static class ProjectRequestRowMapper implements RowMapper<ProjectRequest> {

        public ProjectRequest mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            ProjectRequest projectRequest = ProjectRequest.getProjectRequest();
            projectRequest.setId(resultSet.getLong("id"));
            projectRequest.setExternalName(resultSet.getString("c_exter_name"));
            projectRequest.setSpecLink(resultSet.getString("c_specs_link"));
            projectRequest.setCustomerName(resultSet.getString("c_customer_name"));
            projectRequest.setCustomerEmail(resultSet.getString("c_customer_email"));
            return projectRequest;
        }
    }

}

