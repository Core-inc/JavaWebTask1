package com.teamcore.manageapp.service.domain;


import java.time.LocalDateTime;

public class Task extends AbstractDomainClass {
    private String name;

    private long cost;

    private long duration;

    private int status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Project project;

}
