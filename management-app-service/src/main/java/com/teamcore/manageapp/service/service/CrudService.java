package com.teamcore.manageapp.service.service;

import java.util.List;

public interface CrudService<T> {

    List<T> getAll();

    T getById(Long id);

    T save(T domainObject);

    T update(T domainObject);

    void delete(Long id);
}
