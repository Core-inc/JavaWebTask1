package com.teamcore.manageapp.web.controllers.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class RestError {

    @JsonProperty("error_code")
    private int code;
    @JsonProperty("message")
    private String message;

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

    public enum Code {

        GENERAL_REST_ERROR(1, HttpStatus.NOT_FOUND),
        SKILLS_NOT_FOUND_CODE(2, HttpStatus.NOT_FOUND),
        SKILL_NOT_FOUND_CODE(3, HttpStatus.NOT_FOUND),

        SKILL_DEVELOPERS_NOT_FOUND_CODE(4, HttpStatus.NOT_FOUND),
        SKILL_PROJECTS_NOT_FOUND_CODE(5, HttpStatus.NOT_FOUND),

        //maybe http status codes should be changed
        //according to security policy
        SKILL_ADD_CODE(6, HttpStatus.INTERNAL_SERVER_ERROR),
        SKILL_UPDATE_CODE(7, HttpStatus.INTERNAL_SERVER_ERROR),
        SKILL_DELETE_CODE(8, HttpStatus.INTERNAL_SERVER_ERROR);

        private final HttpStatus httpStatus;
        private final int serviceCode;

        private Code(int serviceCode, HttpStatus httpStatus) {
            this.serviceCode = serviceCode;
            this.httpStatus = httpStatus;
        }

        public int getServiceCode() {
            return serviceCode;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }
    }

}
