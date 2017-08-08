package com.teamcore.manageapp.service.dao.project;


import com.teamcore.manageapp.service.domain.Project;

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
    public Long addNewProject(Project project);


    /**
     * Method to view all projects.
     */
    public List<Project> viewAllProjects();

    /**
     * Method to find a project by it's id.
     * @param id - id of project.
     */
    public Project findById(Long id);

    /**
     * Method for deleting project by it's id.
     * @param id - id of project.
     */
    public String deleteById(Long id);

    /**
     * Method to find a project by it's innerName.
     * @param internalName - innerName of project.
     */
    public Project findByInternalName(String internalName);

    /**
     * Method for deleting project by it's innerName.
     * @param internalName - innerName of project.
     */
    public String deleteByInternalName(String internalName);

    /**
     * Method to update info of the project.
     * @param newProject - project after update.
     */
    public String updateProject(Project newProject);


}
