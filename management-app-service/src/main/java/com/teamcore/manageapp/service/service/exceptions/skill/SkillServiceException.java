package com.teamcore.manageapp.service.service.exceptions.skill;

public class SkillServiceException extends RuntimeException {

    protected String message;

    protected SkillServiceException() {
       message = "Skill service error";
    }

    public SkillServiceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
