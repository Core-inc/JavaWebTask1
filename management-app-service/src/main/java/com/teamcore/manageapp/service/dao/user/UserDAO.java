package com.teamcore.manageapp.service.dao.user;


import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.Skill;
import com.teamcore.manageapp.service.domain.User;

import java.util.List;

public interface UserDAO {
    User save(User user);
    User getById(Integer id);
    User getByEmail(String email);
    void addSkill(User user, Skill skill);
    List<User> getAll();
    void delete(Integer id);
    void delete(User user);
    List<User> getAllByName(String name);
    Role getRoleByUserId(Integer id);
}
