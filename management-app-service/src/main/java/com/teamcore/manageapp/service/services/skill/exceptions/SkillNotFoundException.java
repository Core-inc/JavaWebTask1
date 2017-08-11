package com.teamcore.manageapp.service.services.skill.exceptions;

public class SkillNotFoundException extends SkillServiceException {

    private long skillId;

    public SkillNotFoundException(long skillId) {
        this.skillId = skillId;
        this.message = String.format("Skill [%d] not found", skillId);
    }

    public long getSkillId() {
        return skillId;
    }
}

