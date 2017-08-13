package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.TaskDAO;
import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Task;
import com.teamcore.manageapp.service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDAO taskDAO;


    /**
     * Find task by id
     * @param id
     * @return task with id
     */
    @Override
    public Task findTaskById(long id) {
        return taskDAO.findTaskById(id);
    }

    /**
     * Delete task by id
     * @param id of task to delete
     */
    @Override
    public void deleteTaskById(long id) {
        taskDAO.deleteTaskById(id);
    }

    /**
     * update task in database
     * @param task to update
     */
    @Override
    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }

    /**
     * add task to database
     * @param task to add
     * @return task with id
     */
    @Override
    public Task addTask(Task task) {
        return taskDAO.addTask(task);
    }

    /**
     * find all tasks by project
     * @param project
     * @return list of task of project
     */
    @Override
    public List<Task> findAllTasksByProject(Project project) {
        return taskDAO.findAllTasksByProject(project);
    }

    @Override
    public List<Developer> getDeveloperByTask(Task task) {
        return taskDAO.getDeveloperByTask(task);
    }

    @Override
    public void addDeveloperToTask(Developer developer, Task task) {
        taskDAO.addDeveloperToTask(developer,task);
    }


    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public Task getById(Long id) {
        return findTaskById(id);
    }

    @Override
    public Task save(Task domainObject) {
        //  taskDAO.addDeveloperToTask(developer, domainObject);
        return taskDAO.addTask(domainObject);
    }

    @Override
    public Task update(Task domainObject) {
        taskDAO.updateTask(domainObject);
        return domainObject;
    }

    @Override
    public void delete(Long id) {
        taskDAO.deleteTaskById(id);
    }
}
