package com.teamcore.site.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

/**
 * Created by igoz on 26.07.17.
 */

@MappedSuperclass
public class AbstractDomainClass implements DomainObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
