package com.teamcore.site.services.project;

import com.teamcore.site.domain.Project;
import com.teamcore.site.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by igoz on 26.07.17.
 */

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Project getById(Integer id) {
        return null;
    }

    @Override
    public Project saveOrUpdate(Project domainObject) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
