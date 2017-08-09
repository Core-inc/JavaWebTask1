package com.teamcore.manageapp.service.domain;


import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public @Data class Task {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long cost;

    @NotNull
    private Long duration;

    @NotNull
    private Integer status;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @NotNull
    private Long projectId;


    private Task() { }

    private Task(Task task) {
        this.id = task.id;
        this.name = task.name;
        this.cost = task.cost;
        this.duration = task.duration;
        this.status = task.status;
        this.createdAt = task.createdAt;
        this.updatedAt = task.updatedAt;
        this.projectId = task.projectId;
    }

    public static Builder newBuilder() {
        return new Task().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setId(Long id) {
            Task.this.setId(id);
            return this;
        }

        public Builder setName(String name) {
            Task.this.setName(name);
            return this;
        }

        public Builder setCost(Long cost) {
            Task.this.setCost(cost);
            return this;
        }

        public Builder setDuration(Long duration) {
            Task.this.setDuration(duration);
            return this;
        }

        public Builder setStatus(Integer status) {
            Task.this.setStatus(status);
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            Task.this.setCreatedAt(createdAt);
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            Task.this.setUpdatedAt(updatedAt);
            return this;
        }

        public Builder setProjectId(Long projectId) {
            Task.this.setProjectId(projectId);
            return this;
        }

        public Task build() {
            return new Task(Task.this);
        }
    }
}
