package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.AdminDAO;
import com.teamcore.manageapp.service.domain.Admin;
import com.teamcore.manageapp.service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@see AdminService} Implementation based on {@see AdminDAO} interface
 * for retrieving {@see Admin} objects that represent admin users
 * in our system
 */
@Service
public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO;

    /**
     * setter injection method to setup {@see AdminDAO} object
     * @param adminDAO {@see AdminDAO} object to inject
     */
    @Autowired
    public void setAdminDAO(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    /**
     * retrieves list of all admin users in system
     * @return list of all admin users in system
     * @see Admin
     */
    @Override
    public List<Admin> getAll() {
        return adminDAO.getAll();
    }

    /**
     * retrieves admin user with specific {@code id} from database
     * @param id id of admin user in database
     * @return {@see Admin} object describing requested admin user entity
     * @see Admin
     */
    @Override
    public Admin getById(Long id) {
        return adminDAO.getById(id);
    }

    /**
     * saves specified {@see Admin} object in database
     * @param domainObject {@see Admin} object to save in database
     * @return saved {@see Admin} object
     * @see Admin
     */
    @Override
    public Admin save(Admin domainObject) {
        return adminDAO.save(domainObject);
    }

    /**
     * updates specified {@see Admin} object in database
     * @param domainObject {@see Admin} object to update in database
     * @return updated {@see Admin} object
     * @see Admin
     */
    @Override
    public Admin update(Admin domainObject) {
        return adminDAO.update(domainObject);
    }

    /**
     * deletes admin user with specified {@code id} from database
     * @param id id of the admin user to delete from database
     */
    @Override
    public void delete(Long id) {
        adminDAO.delete(id);
    }
}
