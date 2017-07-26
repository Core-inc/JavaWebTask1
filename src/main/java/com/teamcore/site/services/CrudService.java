package com.teamcore.site.services;

import java.util.List;

/**
 * Created by igoz on 26.07.17.
 */
public interface CrudService<T> {

    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void delete(Integer id);
}
