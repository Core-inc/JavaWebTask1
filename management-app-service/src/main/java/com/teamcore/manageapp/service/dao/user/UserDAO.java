package com.teamcore.manageapp.service.dao.user;


import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.Skill;
import com.teamcore.manageapp.service.domain.User;

import java.util.List;

public interface UserDAO {
    User saveOrUpdate(User user);
    User getById(Long id);
    User getByEmail(String email);
    void addSkill(User user, Skill skill);
    List<User> getAll();
    void delete(Long id);
    void delete(User user);
    List<User> getAllByName(String name);
    Role getRoleByUserId(Long id);
}
