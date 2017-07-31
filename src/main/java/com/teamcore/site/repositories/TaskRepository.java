package com.teamcore.site.repositories;

import com.teamcore.site.domain.Task;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by igoz on 26.07.17.
 */
public interface TaskRepository extends CrudRepository<Task, Integer> {
}
