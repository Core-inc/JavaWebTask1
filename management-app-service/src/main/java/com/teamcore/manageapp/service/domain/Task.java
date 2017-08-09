package com.teamcore.manageapp.service.domain;


import lombok.Data;

import java.time.LocalDateTime;

public @Data class Task {
    private Long id;
    private String name;
    private long cost;
    private long duration;
    private int status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Project project;

}
