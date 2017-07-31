package com.teamcore.site.dao.user;

import com.teamcore.site.domain.User;

/**
 * Created by igoz on 31.07.17.
 */
public interface UserDAO {
    User addUser(User user);
    User getUserById(Integer id);
}
