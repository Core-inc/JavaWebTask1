package com.teamcore.manageapp.service.service.exceptions.skill;

public class SkillDevelopersNotFoundException extends SkillServiceException {

    public SkillDevelopersNotFoundException() {
        this.message = "Developers not found";
    }
}
