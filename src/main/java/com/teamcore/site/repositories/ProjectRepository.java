package com.teamcore.site.repositories;

import com.teamcore.site.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
}
