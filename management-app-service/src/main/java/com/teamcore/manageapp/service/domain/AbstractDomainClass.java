package com.teamcore.manageapp.service.domain;

public class AbstractDomainClass implements DomainObject {
    private Integer id;

    protected AbstractDomainClass() {}

    protected AbstractDomainClass(AbstractDomainClass domain) {
       this.id = domain.id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
