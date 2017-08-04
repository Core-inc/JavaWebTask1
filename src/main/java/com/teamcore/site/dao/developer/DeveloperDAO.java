package com.teamcore.site.dao.developer;

import com.teamcore.site.domain.Developer;

import java.util.List;

public interface DeveloperDAO {
    Developer getDeveloperById(Integer id);
    Developer getDeveloperByEmail(String email);
    List<Developer> getAllDevelopers();
}
