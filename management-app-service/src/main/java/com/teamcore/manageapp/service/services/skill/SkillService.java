package com.teamcore.manageapp.service.services.skill;

import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> findAll();

    Skill findById(int skillId);

    List<Developer> findAllDevelopers(int skillId);

    List<Developer> findFreeDevelopers(int skillId);

    List<Project> findProjects(int skillId);

}
