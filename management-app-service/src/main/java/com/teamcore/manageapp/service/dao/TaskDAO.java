package com.teamcore.manageapp.service.dao;


import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Task;

import java.util.List;

public interface TaskDAO {

    Task findTaskById(long id);

    void deleteTaskById(long id);

    void updateTask(Task task);

    Task addTask(Task task);

    List<Task> findAllTasksByProject(Project project);

}
