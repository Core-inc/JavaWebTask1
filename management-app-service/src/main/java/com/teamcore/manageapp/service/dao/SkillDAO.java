package com.teamcore.manageapp.service.dao;


import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Skill;

import java.util.List;

public interface SkillDAO {
    Skill addSkill(Skill skill);
    Skill updateSkill(Skill skill);
    void deleteSkill(Long id);

    Skill getSkillById(Long id);
    List<Skill> getAllSkills();
    List<Developer> getAllDevelopersBySkillId(Long id);
    List<Developer> getFreeDevelopersBySkillId(Long id);
    List<Project> getProjectsBySkillId(Long id);
}
