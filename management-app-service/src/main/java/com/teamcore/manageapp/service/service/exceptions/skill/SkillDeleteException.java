package com.teamcore.manageapp.service.service.exceptions.skill;

public class SkillDeleteException extends SkillServiceException {

    public SkillDeleteException() {
        this.message = "Failed to delete skill";
    }
}
