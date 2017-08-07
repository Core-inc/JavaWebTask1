package com.epam.managementapp.service.dao.project;

import com.epam.managementapp.service.domain.Project;

import java.sql.SQLException;
import java.util.*;

/**
 * DAO for Project
 */
public interface ProjectDAO {
    /**
     * Method for adding new Project.
     * @param project - Project to add.
     */
    public int addNewProject(Project project);

    /**
     * Method to view all projects.
     */
    public List<Project> viewAllProjects();

    /**
     * Method to find a project by it's id.
     * @param id - id of project.
     */
    public Project findById(int id);

    /**
     * Method for deleting project by it's id.
     * @param id - id of project.
     */
    public String deleteById(int id);

    /**
     * Method to find a project by it's innerName.
     * @param innerName - innerName of project.
     */
    public Project findByInnerName(String innerName);

    /**
     * Method for deleting project by it's innerName.
     * @param innerName - innerName of project.
     */
    public String deleteByInnerName(String innerName);

    /**
     * Method to update info of the project.
     * @param id - id of the old project.
     * @param newProject - project after update.
     */
    public String updateProject(Project newProject);

    /**
     * Method to find all projects that were created after the specified date.
     * @param date - specified date.
     */
    public List<Project> findAfterTheDate(Date date);

}
