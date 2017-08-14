package com.teamcore.manageapp.service.service;

import com.teamcore.manageapp.service.domain.Skill;

public interface SkillService extends CrudService<Skill> {
    Skill getSkillByDeveloperId(Long id);
}
