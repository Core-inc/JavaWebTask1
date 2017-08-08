package com.teamcore.manageapp.service.dao.skill;


import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Skill;

import java.util.List;

public interface SkillDAO {
    Skill addSkill(Skill skill);
    Skill updateSkill(Skill skill);
    void deleteSkill(int id);

    Skill getSkillById(int id);
    List<Skill> getAllSkills();
    List<Developer> getAllDevelopersBySkillId(int id);
    List<Developer> getFreeDevelopersBySkillId(int id);
    List<Project> getProjectsBySkillId(int id);
}
