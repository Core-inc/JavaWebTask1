package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.ProjectDAO;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);


    @Autowired
    private ProjectDAO projectDAO;


    @Autowired
    public void setProjectDAO(ProjectDAO projectDAO) {

        this.projectDAO = projectDAO;
    }

    public ProjectServiceImpl(ProjectDAO projectDAO){
        this.projectDAO = projectDAO;
    }

    @Override
    public List<Project> getAll() {
        LOGGER.debug("Starting to view all projects: ");
        List<Project> list = projectDAO.viewAllProjects();
        LOGGER.debug("Listing prijects is finished. Projects: {}", list);
        return list;
    }

    @Override
    public Project getById(Long id) {
        LOGGER.debug("Starting to find project: Params id = {}", id);
        Project project = projectDAO.findById(id);
        LOGGER.debug("Project was found. Project = {} ", project);
        return project;
    }

    @Override
    public Project save(Project project) {
        LOGGER.debug("Adding new project: Project = {}", project);
        Project savedProject = projectDAO.addNewProject(project);
        LOGGER.debug("Adding is finished. Project from db = {}: ", savedProject);
        return savedProject;
    }

    @Override
    public Project update(Project newProject) {
        LOGGER.debug("Starting to update project: New project = {}", newProject);
        projectDAO.updateProject(newProject);
        LOGGER.debug("Project was updated: New project= {}", newProject);
        return newProject;
    }

    @Override
    public void delete(Long id) {
        LOGGER.debug("Starting to delete project: Params id = {}", id);
        projectDAO.deleteById(id);
        LOGGER.debug("Project was deleted: Params id= {}", id);

    }

    @Override
    public Project findByInternalName(String internalName) {
        LOGGER.debug("Starting to find project: Params internalName = {}", internalName);
        Project project = projectDAO.findByInternalName(internalName);
        LOGGER.debug("Project was found: Params internalName= {}", internalName);
        return project;
    }

    @Override
    public void deleteByInternalName(String internalName) {
        LOGGER.debug("Starting to delete project: Params innername = {}", internalName);
        projectDAO.deleteByInternalName(internalName);

        LOGGER.debug("Project was deleted: Params innername= {}", internalName);

    }


}
