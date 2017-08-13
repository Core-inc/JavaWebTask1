package com.teamcore.manageapp.service.service;

import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;
import com.teamcore.manageapp.service.service.CrudService;

import java.util.List;

/**
 * An interface for managing {@see User} objects in our system
 */
public interface UserService extends CrudService<User> {

    /**
     * get {@see User} object by specified email
     * @param email - {@code email} of the {@see User}
     * @return {@see User} with this email
     */
    User getByEmail(String email);

    /**
     * get list of {@see User} objects with specified name
     * @param name - {@code name} of the {@see User}
     * @return List of {@see User} objects with this name
     */
    List<User> getAllByName(String name);

    /**
     * get {@see Role} object of {@see User} with specified {@code id}
     * @param id - {@code id} of the {@see User} whose {@see Role} will be returned
     * @return {@see Role}
     */
    Role getRoleByUserId(Long id);
}
