package com.teamcore.manageapp.service.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class Manager extends User {
    private List<Project> projects = new ArrayList<>();
}
