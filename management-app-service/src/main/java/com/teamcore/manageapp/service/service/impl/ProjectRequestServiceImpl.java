package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.ProjectRequestDAO;
import com.teamcore.manageapp.service.domain.ProjectRequest;
import com.teamcore.manageapp.service.service.ProjectRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectRequestServiceImpl implements ProjectRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);


    @Autowired
    private ProjectRequestDAO projectRequestDAO;


    @Autowired
    public void setProjectRequestDAO(ProjectRequestDAO projectRequestDAO) {

        this.projectRequestDAO = projectRequestDAO;
    }

    public ProjectRequestServiceImpl(ProjectRequestDAO projectRequestDAO){
        this.projectRequestDAO = projectRequestDAO;
    }

    @Override
    public List<ProjectRequest> getAll() {
        LOGGER.debug("Starting to view all project requests: ");
        List<ProjectRequest> list = projectRequestDAO.viewAllProjectRequests();
        LOGGER.debug("Listing priject requests is finished. Project requests: {}", list);
        return list;
    }

    @Override
    public ProjectRequest getById(Long id) {
        LOGGER.debug("Starting to find project request: Params id = {}", id);
        ProjectRequest projectRequest = projectRequestDAO.findById(id);
        LOGGER.debug("Project request was found. Project request = {} ", projectRequest);
        return projectRequest;
    }

    @Override
    public ProjectRequest save(ProjectRequest projectRequest) {
        LOGGER.debug("Adding new project request: Project request = {}", projectRequest);
        ProjectRequest savedProjectRequest = projectRequestDAO.addNewProjectRequest(projectRequest);
        LOGGER.debug("Adding is finished. Project request from db = {}: ", savedProjectRequest);
        return savedProjectRequest;
    }

    @Override
    public ProjectRequest update(ProjectRequest newProjectRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {
        LOGGER.debug("Starting to delete project request: Params id = {}", id);
        projectRequestDAO.deleteById(id);
        LOGGER.debug("Project request was deleted: Params id= {}", id);

    }

    @Override
    public ProjectRequest findByCustomerName(String customerName) {
        LOGGER.debug("Starting to find project request: Params customerName = {}", customerName);
        ProjectRequest projectRequest = projectRequestDAO.findByCustomerName(customerName);
        LOGGER.debug("Project request was found: Params customerName= {}", customerName);
        return projectRequest;
    }

}
