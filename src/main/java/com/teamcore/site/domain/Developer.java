package com.teamcore.site.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public @Data class Developer extends User {
    private List<Skill> skills = new ArrayList<>();
}
