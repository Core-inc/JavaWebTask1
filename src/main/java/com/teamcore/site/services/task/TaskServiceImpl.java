package com.teamcore.site.services.task;

import com.teamcore.site.domain.Task;
import com.teamcore.site.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by igoz on 26.07.17.
 */

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Task getById(Integer id) {
        return null;
    }

    @Override
    public Task saveOrUpdate(Task domainObject) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
