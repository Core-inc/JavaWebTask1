package com.teamcore.manageapp.service.service.exceptions.skill;

public class SkillProjectsNotFoundException extends SkillServiceException {

    public SkillProjectsNotFoundException() {
        this.message = "Projects not found";
    }
}
