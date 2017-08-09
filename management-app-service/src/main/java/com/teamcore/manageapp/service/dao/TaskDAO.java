package com.teamcore.manageapp.service.dao;



import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Task;
import com.teamcore.manageapp.service.domain.User;

import java.util.List;

/**
 * Created by alterG on 1.08.17.
 */
public interface TaskDAO {

    // manager (assign task with users)
    void addUserToTask(User user);

    // manager and developer (browse developer portfolio)
    List<Task> getTaskListbyUser(User user);

    // manager (browse project tasks)
    List<Task> getTaskListbyProject(Project project);
}
