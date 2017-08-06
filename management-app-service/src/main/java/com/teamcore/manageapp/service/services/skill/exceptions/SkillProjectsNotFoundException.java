package com.teamcore.manageapp.service.services.skill.exceptions;

public class SkillProjectsNotFoundException extends SkillServiceException {

    public SkillProjectsNotFoundException() {
        this.message = "Projects not found";
    }
}
