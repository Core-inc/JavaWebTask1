package com.teamcore.manageapp.service.dao.user;


import com.teamcore.manageapp.service.domain.Skill;
import com.teamcore.manageapp.service.domain.User;

public interface UserDAO {
    User addUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
    void addSkillToUser(User user, Skill skill);
}
