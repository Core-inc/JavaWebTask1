package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.ManagerDAO;
import com.teamcore.manageapp.service.domain.Manager;
import com.teamcore.manageapp.service.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@see ManagerService} Implementation based on {@see ManagerDAO} interface
 * for retrieving {@see Manager} objects that represent manager users
 * in our system
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerDAO managerDAO;

    /**
     * setter injection method to setup {@see ManagerDAO} object
     * @param managerDAO {@see ManagerDAO} object to inject
     * @see ManagerDAO
     */
    @Autowired
    public void setManagerDAO(ManagerDAO managerDAO) {
        this.managerDAO = managerDAO;
    }

    /**
     * retrieves list of all manager users in system
     * @return list of all manager users in system
     * @see Manager
     */
    @Override
    public List<Manager> getAll() {
        return managerDAO.getAll();
    }

    /**
     * retrieves manager user with specific {@code id} from database
     * @param id id of manager user in database
     * @return {@see Manager} object describing requested manager user entity
     * @see Manager
     */
    @Override
    public Manager getById(Long id) {
       return managerDAO.getById(id);
    }

    /**
     * saves specified {@see Manager} object in database
     * @param domainObject {@see Manager} object to save in database
     * @return saved {@see Manager} object
     * @see Manager
     */
    @Override
    public Manager save(Manager domainObject) {
        return managerDAO.save(domainObject);
    }

    /**
     * updates specified {@see Manager} object in database
     * @param domainObject {@see Manager} object to update in database
     * @return updated {@see Manager} object
     * @see Manager
     */
    @Override
    public Manager update(Manager domainObject) {
        return managerDAO.update(domainObject);
    }

    /**
     * deletes manager user with specified {@code id} from database
     * @param id id of the manager user to delete from database
     */
    @Override
    public void delete(Long id) {
        managerDAO.delete(id);
    }
}
