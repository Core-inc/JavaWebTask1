package com.teamcore.site.repositories;

import com.teamcore.site.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}
