package com.teamcore.manageapp.service.services.skill.exceptions;

public class SkillNotFoundException extends SkillServiceException {

    private int skillId;

    public SkillNotFoundException(int skillId) {
        this.skillId = skillId;
        this.message = String.format("Skill [%d] not found", skillId);
    }

    public int getSkillId() {
        return skillId;
    }
}

