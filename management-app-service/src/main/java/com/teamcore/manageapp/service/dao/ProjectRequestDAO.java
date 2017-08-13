package com.teamcore.manageapp.service.dao;

import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.ProjectRequest;

import java.util.List;

/**
 * DAO for ProjectRequest
 */
public interface ProjectRequestDAO {
    /**
     * Method for adding new ProjectRequest.
     * @param projectRequest - ProjectRequest to add.
     */
    public ProjectRequest addNewProjectRequest(ProjectRequest projectRequest);


    /**
     * Method to view all project requests.
     */
    public List<ProjectRequest> viewAllProjectRequests();

    /**
     * Method to find a project request by it's id.
     * @param id - id of projectRequest.
     */
    public ProjectRequest findById(Long id);

    /**
     * Method for deleting project request by it's id.
     * @param id - id of projectRequest.
     */
    public void deleteById(Long id);

    /**
     * Method to find a project request by it's customerName.
     * @param customerName - name of customer.
     */
    public ProjectRequest findByCustomerName(String customerName);


}
