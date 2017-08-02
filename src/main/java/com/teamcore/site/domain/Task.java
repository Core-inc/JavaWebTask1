package com.teamcore.site.domain;

import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by alterG on 1.08.17.
 */

@Entity
@Table(name = "t_tasks")
public class Task extends AbstractDomainClass {

    @Column(nullable = false)
    private String name;

    @Column
    private long cost;

    @Column
    private long duration;

    @Column
    private int status;

    @Column(nullable = false)
    private DateTime createdAt;

    @Column(nullable = false)
    private DateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

}
