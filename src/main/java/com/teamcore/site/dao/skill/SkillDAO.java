package com.teamcore.site.dao.skill;

import com.teamcore.site.domain.Skill;

public interface SkillDAO {
    Skill addSkill(Skill skill);
    Skill getSkillById(Long id);
}
