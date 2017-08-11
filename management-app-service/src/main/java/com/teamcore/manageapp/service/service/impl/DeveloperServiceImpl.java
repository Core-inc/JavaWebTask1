package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.DeveloperDAO;
import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@see DeveloperService} Implementation based on {@see DeveloperDAO} interface
 * for retrieving {@see Developer} objects that represent developer users
 * in our system
 */
@Service
public class DeveloperServiceImpl implements DeveloperService {

    private DeveloperDAO developerDAO;

    /**
     * setter injection method to setup {@see DeveloperDAO} object
     * @param developerDAO {@see DeveloperDAO} object to inject
     * @see DeveloperDAO
     */
    @Autowired
    public void setDeveloperDAO(DeveloperDAO developerDAO) {
        this.developerDAO = developerDAO;
    }

    /**
     * retrieves list of all developer users in system
     * @return list of all developer users in system
     * @see Developer
     */
    @Override
    public List<Developer> getAll() {
        return developerDAO.getAll();
    }

    /**
     * retrieves developer user with specific {@code id} from database
     * @param id id of developer user in database
     * @return {@see Developer} object describing requested developer user entity
     * @see Developer
     */
    @Override
    public Developer getById(Long id) {
        return developerDAO.getById(id);
    }

    /**
     * saves specified {@see Developer} object in database
     * @param domainObject {@see Developer} object to save in database
     * @return saved {@see Developer} object
     * @see Developer
     */
    @Override
    public Developer save(Developer domainObject) {
        return developerDAO.save(domainObject);
    }

    /**
     * updates specified {@see Developer} object in database
     * @param domainObject {@see Developer} object to update in database
     * @return updated {@see Developer} object
     * @see Developer
     */
    @Override
    public Developer update(Developer domainObject) {
        return developerDAO.update(domainObject);
    }

    /**
     * deletes developer user with specified {@code id} from database
     * @param id id of the developer user to delete from database
     */
    @Override
    public void delete(Long id) {
        developerDAO.delete(id);
    }
}
