package com.teamcore.manageapp.service.service;

import com.teamcore.manageapp.service.domain.Project;

public interface ProjectService extends CrudService<Project> {

    /**
     * Method for adding new Project.
     * @param project - Project to add.
     */
    //   public int addNewProject(Project project);

    /**
     * Method to view all projects.
     */
    //   public List<Project> viewAllProjects();

    /**
     * Method to find a project by it's id.
     * @param id - id of project.
     */
    //   public Project findById(int id);

    /**
     * Method for deleting project by it's id.
     * @param id - id of project.
     */
    //   public void deleteById(int id);


    /**
     * Method to find a project by it's innerName.
     * @param internalName - innerName of project.
     */
    public Project findByInternalName(String internalName);

    /**
     * Method for deleting project by it's innerName.
     * @param internalName - innerName of project.
     */
    public void deleteByInternalName(String internalName);

    /**
     * Method to update info of the project.
     * @param newProject - project after update.
     */
    //  public int updateProject(Project newProject);
}
