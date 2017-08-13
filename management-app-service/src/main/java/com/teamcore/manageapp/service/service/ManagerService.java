package com.teamcore.manageapp.service.service;

import com.teamcore.manageapp.service.domain.Manager;

import java.util.List;

/**
 * An interface for managing {@see Manager} objects in our system
 */
public interface ManagerService extends CrudService<Manager> {
    /**
     * get {@see Manager} object by specified email
     * @param email - {@code email} of the {@see Manager}
     * @return {@see Manager} with this email
     */
    Manager getByEmail(String email);

    /**
     * get list of {@see Manager} objects with specified name
     * @param name - {@code name} of the {@see Manager}
     * @return List of {@see Manager} objects with this name
     */
    List<Manager> getAllByName(String name);
}
