package com.teamcore.manageapp.service.services.skill.exceptions;

public class SkillDevelopersNotFoundException extends SkillServiceException {

    public SkillDevelopersNotFoundException() {
        this.message = "Developers not found";
    }
}
