package com.teamcore.manageapp.service.service;


import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.ProjectRequest;

public interface ProjectRequestService extends CrudService<ProjectRequest> {


    /**
     * Method to find a project request by it's innerName.
     * @param customerName - customerName of project request.
     */
    public ProjectRequest findByCustomerName(String customerName);

}
