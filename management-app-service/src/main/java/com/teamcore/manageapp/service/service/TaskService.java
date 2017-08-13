package com.teamcore.manageapp.service.service;

import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Task;
import com.teamcore.manageapp.service.service.CrudService;

import java.util.List;

public interface TaskService extends CrudService<Task> {

    /**
     * Find task by id
     * @param id
     * @return task with id
     */
    Task findTaskById(long id);

    /**
     * Delete task by id
     * @param id of task to delete
     */
    void deleteTaskById(long id);

    /**
     * update task in database
     * @param task to update
     */
    void updateTask(Task task);

    /**
     * add task to database
     * @param task to add
     * @return task with id
     */
    Task addTask(Task task);

    /**
     * find all tasks by project
     * @param project
     * @return list of task of project
     */
    List<Task> findAllTasksByProject(Project project);

}
