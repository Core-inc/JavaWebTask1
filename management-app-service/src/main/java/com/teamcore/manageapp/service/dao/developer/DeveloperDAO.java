package com.teamcore.manageapp.service.dao.developer;


import com.teamcore.manageapp.service.domain.Developer;

import java.util.List;

public interface DeveloperDAO {
    Developer getDeveloperById(Integer id);
    Developer getDeveloperByEmail(String email);
    List<Developer> getAllDevelopers();
}
