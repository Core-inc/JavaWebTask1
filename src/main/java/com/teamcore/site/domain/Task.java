package com.teamcore.site.domain;

import org.joda.time.DateTime;

public class Task extends AbstractDomainClass {
    private String name;

    private long cost;

    private long duration;

    private int status;

    private DateTime createdAt;

    private DateTime updatedAt;

    private Project project;

}
