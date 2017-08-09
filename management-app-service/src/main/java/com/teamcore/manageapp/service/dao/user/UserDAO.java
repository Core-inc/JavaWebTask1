package com.teamcore.manageapp.service.dao.user;

import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;

import java.util.List;

public interface UserDAO {
    User save(User user);
    User update(User user);
    User getById(Long id);
    User getByEmail(String email);
    List<User> getAll();
    void delete(Long id);
    void delete(User user);
    List<User> getAllByName(String name);
    Role getRoleByUserId(Long id);
}
