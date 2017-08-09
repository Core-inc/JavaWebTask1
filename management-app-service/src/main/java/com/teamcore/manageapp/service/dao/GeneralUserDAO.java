package com.teamcore.manageapp.service.dao;

import com.teamcore.manageapp.service.domain.User;

import java.util.List;

interface GeneralUserDAO<T extends User> {
    T save(T user);
    T update(T user);
    void delete(Long id);

    T getById(Long id);
    T getByEmail(String email);
    List<T> getAll();

    List<T> getAllByName(String name);
}
