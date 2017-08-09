package com.teamcore.manageapp.service.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

public @Data class Project {

    private Long id;
    private String externalName;
    private String internalName;
    private String specLink;

    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Project() {}

    private Project(Project project) {
        this.externalName = project.externalName;
        this.internalName = project.internalName;
        this.specLink = project.specLink;
        this.status = project.status;
        this.createdAt = project.createdAt;
        this.updatedAt = project.updatedAt;
    }

    public static Project getProject(){
        return new Project();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id =id;
    }

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public String getSpecLink() {
        return specLink;
    }

    public void setSpecLink(String specLink) {
        this.specLink = specLink;
    }

    public int getStatus() {return status;}

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "id = "+id+" |externalName = "+externalName+ " |internalName= "+internalName + " |status="+Status.values()[status].toString()
                +" |specLink = "+specLink + " |createdAt = "+createdAt+ " |updatedAt = "+updatedAt;
    }


    public static Builder newBuilder() {
        return new Project().new Builder();
    }

    public class Builder {

        private Builder() {}

        public Builder setId(Long id) {
            Project.this.setId(id);
            return this;
        }

        public Builder setExternalName(String externalName) {
            Project.this.setExternalName(externalName);
            return this;
        }

        public Builder setInternalName(String internalName) {
            Project.this.setInternalName(internalName);
            return this;
        }

        public Builder setSpecLink(String specLink) {
            Project.this.setSpecLink(specLink);
            return this;
        }

        public Builder setStatus(Integer status) {
            Project.this.setStatus(status);
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            Project.this.setCreatedAt(createdAt);
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            Project.this.setUpdatedAt(updatedAt);
            return this;
        }

        public Project build() {
            return new Project(Project.this);
        }

    }

    enum Status {IN_PROCESS,FINISHED,CANCELED}

}
//enum Status {IN_PROCESS,FINISHED,CANCELED}
