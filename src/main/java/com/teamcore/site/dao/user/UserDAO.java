package com.teamcore.site.dao.user;

import com.teamcore.site.domain.Skill;
import com.teamcore.site.domain.User;

public interface UserDAO {
    User addUser(User user);
    User getUserById(Integer id);
    User getUserByEmail(String email);
    void addSkillToUser(User user, Skill skill);
}
