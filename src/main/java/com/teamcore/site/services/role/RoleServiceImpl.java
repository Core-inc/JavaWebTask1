package com.teamcore.site.services.role;

import com.teamcore.site.domain.Role;
import com.teamcore.site.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by igoz on 26.07.17.
 */

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public Role getById(Integer id) {
        return null;
    }

    @Override
    public Role saveOrUpdate(Role domainObject) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
