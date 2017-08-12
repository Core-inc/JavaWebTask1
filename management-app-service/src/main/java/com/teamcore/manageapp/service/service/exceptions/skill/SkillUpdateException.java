package com.teamcore.manageapp.service.service.exceptions.skill;

public class SkillUpdateException extends SkillServiceException {

    public SkillUpdateException() {
        this.message = "Failed to update skill";
    }
}
