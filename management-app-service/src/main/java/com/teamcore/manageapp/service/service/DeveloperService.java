package com.teamcore.manageapp.service.service;

import com.teamcore.manageapp.service.domain.Developer;

import java.util.List;

/**
 * An interface for managing {@see Developer} objects in our system
 */
public interface DeveloperService extends CrudService<Developer> {
    /**
     * get {@see Developer} object by specified email
     * @param email - {@code email} of the {@see Developer}
     * @return {@see Developer} with this email
     */
    Developer getByEmail(String email);

    /**
     * get list of {@see Developer} objects with specified name
     * @param name - {@code name} of the {@see Developer}
     * @return List of {@see Developer} objects with this name
     */
    List<Developer> getAllByName(String name);

    String getDeveloperStatus(Long id);
}
