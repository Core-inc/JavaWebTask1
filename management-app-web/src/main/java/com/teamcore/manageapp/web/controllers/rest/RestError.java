package com.teamcore.manageapp.web.controllers.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestError {

    @JsonProperty("error_code")
    private int code;
    @JsonProperty("message")
    private String message;


    public final static int GENERAL_REST_ERROR = 1;
    public final static int SKILLS_NOT_FOUND_CODE = 2;
    public final static int SKILL_NOT_FOUND_CODE = 3;
    public final static int SKILL_DEVELOPERS_NOT_FOUND_CODE = 4;
    public final static int SKILL_PROJECTS_NOT_FOUND = 5;

    private RestError() {}

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static Builder newBuilder() {
        return new RestError().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setCode(int code) {
            RestError.this.code = code;
            return this;
        }

        public Builder setMessage(String message) {
            RestError.this.message = message;
            return this;
        }

        public RestError build() {
            return RestError.this;
        }

    }
}
