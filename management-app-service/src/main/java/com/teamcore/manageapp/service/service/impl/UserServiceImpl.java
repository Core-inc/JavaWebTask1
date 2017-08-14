package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.UserDAO;
import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;
import com.teamcore.manageapp.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@see UserService} Implementation based on {@see UserDAO} interface
 * for retrieving {@see User} objects that represent users
 * in our system
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    /**
     * setter injection method to setup {@see UserDAO} object
     * @param userDAO {@see UserDAO} object to inject
     * @see UserDAO
     */
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * retrieves list of all users in system
     * @return list of all users in system
     * @see User
     */
    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    /**
     * retrieves user with specific {@code id} from database
     * @param id id of user in database
     * @return {@see User} object describing requested user entity
     * @see User
     */
    @Override
    public User getById(Long id) {
        return userDAO.getById(id);
    }

    /**
     * saves specified {@see User} object in database
     * @param domainObject {@see User} object to save in database
     * @return saved {@see User} object
     * @see User
     */
    @Override
    public User save(User domainObject) {
        return userDAO.save(domainObject);
    }

    /**
     * updates specified {@see User} object in database
     * @param domainObject {@see User} object to update in database
     * @return updated {@see User} object
     * @see User
     */
    @Override
    public User update(User domainObject) {
        return userDAO.update(domainObject);
    }

    /**
     * deletes user with specified {@code id} from database
     * @param id id of the user to delete from database
     */
    @Override
    public void delete(Long id) {
        userDAO.delete(id);
    }

    /**
     * get {@see User} object by specified email
     * @param email - {@code email} of the {@see User}
     * @return {@see User} with this email
     */
    @Override
    public User getByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    /**
     * get list of {@see User} objects with specified name
     * @param name - {@code name} of the {@see User}
     * @return List of {@see User} objects with this name
     */
    @Override
    public List<User> getAllByName(String name) {
        return userDAO.getAllByName(name);
    }

    /**
     * get {@see Role} object of {@see User} with specified {@code id}
     * @param id - {@code id} of the {@see User} whose {@see Role} will be returned
     * @return {@see Role}
     */
    @Override
    public Role getRoleByUserId(Long id) {
        return userDAO.getRoleByUserId(id);
    }

    @Override
    public List<User> getAllCustomers() {
        return userDAO.getAllCustomers();
    }

    @Override
    public void addCustomerProject(Long customerId, Long projectId) {
        userDAO.addCustomerProject(customerId, projectId);
    }
}
