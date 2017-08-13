package com.teamcore.manageapp.service.dao;

import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;

import java.util.List;

/**
 * UserDAO is a data-access object that manipulates
 * user entities
 */
public interface UserDAO extends GeneralUserDAO<User> {

    /**
     * returns {@see Role} enum for user entity with specific {@code id}
     * @param id id of user in database
     * @see Role
     */
    Role getRoleByUserId(Long id);

    List<User> getAllCustomers();
}
