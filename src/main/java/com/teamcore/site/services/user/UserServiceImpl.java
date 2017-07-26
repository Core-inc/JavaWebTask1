package com.teamcore.site.services.user;

import com.teamcore.site.domain.User;
import com.teamcore.site.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by igoz on 26.07.17.
 */

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<?> listAll() {
        return null;
    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
