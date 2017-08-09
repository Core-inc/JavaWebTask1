package com.teamcore.manageapp.service.dao;

import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;

import java.util.List;

public interface UserDAO extends GeneralUserDAO<User> {

    Role getRoleByUserId(Long id);
}
