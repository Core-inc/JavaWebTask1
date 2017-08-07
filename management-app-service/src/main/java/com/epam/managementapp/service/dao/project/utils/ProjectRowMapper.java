package com.epam.managementapp.service.dao.project.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.managementapp.service.domain.Project;
import org.springframework.jdbc.core.RowMapper;


public class ProjectRowMapper implements RowMapper<Project> {

    public Project mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Project project = new Project();
        project.setId(resultSet.getInt("id"));
        project.setOuterName(resultSet.getString("outername"));
        project.setInnerName(resultSet.getString("innername"));
        project.setTechFile(resultSet.getString("techfile"));
        project.setStatus(resultSet.getString("status"));
        project.setCreatedAt(resultSet.getDate("createdat"));
        project.setUpdatedAt(resultSet.getDate("updatedat"));
        return project;
    }
}