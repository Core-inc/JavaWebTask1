package com.teamcore.manageapp.service.dao;

import com.teamcore.manageapp.service.domain.User;

import java.util.List;

/**
 * An interface which describes general operations
 * on {@see User}-based data-access objects
 * @see User
 */
interface GeneralUserDAO<T extends User> {
    /**
     * saves specified generic {@see User}-derived object in database
     * @param user {@see User}-derived object to save in database
     * @return saved {@see User}-derived object
     * @see User
     */
    T save(T user);
    /**
     * updates specified {@see User}-derived object in database
     * @param user {@see User}-derived object to update in database
     * @return updated {@see User} object
     * @see User
     */
    T update(T user);
    /**
     * deletes user with specified {@code id} from database
     * @param id id of the user to delete from database
     */
    void delete(Long id);
    /**
     * retrieves user with specific {@code id} from database
     * @param id id of the user in database
     * @return {@see User}-derived object describing requested user entity
     * @see User
     */
    T getById(Long id);
    /**
     * retrieves user with specific {@code email} from database
     * @param email email of user in database
     * @return {@see User}-derived object describing requested user entity
     * @see User
     */
    T getByEmail(String email);
    /**
     * retrieves list of all users in system
     * @return list of all users in system
     * @see User
     */
    List<T> getAll();
    /**
     * retrieves list of all users in system with specified {@code name}
     * @return list of all users in system with specified {@code name}
     * @see User
     */
    List<T> getAllByName(String name);
}
