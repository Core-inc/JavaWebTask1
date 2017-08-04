package com.teamcore.manageapp.service.dao.skill;


import com.teamcore.manageapp.service.domain.Skill;

public interface SkillDAO {
    Skill addSkill(Skill skill);
    Skill getSkillById(Long id);
}
