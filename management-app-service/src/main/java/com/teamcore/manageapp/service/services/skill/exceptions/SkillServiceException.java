package com.teamcore.manageapp.service.services.skill.exceptions;

public class SkillServiceException extends RuntimeException {

    protected String message;

    protected SkillServiceException() {}

    public SkillServiceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
