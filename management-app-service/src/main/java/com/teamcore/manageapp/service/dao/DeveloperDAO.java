package com.teamcore.manageapp.service.dao;


import com.teamcore.manageapp.service.domain.Developer;

/**
 * DeveloperDAO is a data-access object that manipulates
 * developer user entities
 */
public interface DeveloperDAO extends GeneralUserDAO<Developer> {
    Boolean getDeveloperStatus(Long id);

//    List<Developer> getAllBySkillId(Long id);
//    List<Developer> getFreeBySkillId(Long id);
    //List<Skill> getSkillsByDeveloperId(Long id);
}
