package com.teamcore.site.dao.user;

import com.teamcore.site.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igoz on 31.07.17.
 */

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String query = "INSERT INTO t_users (name, email, password, salt, created_at) values (?, ?, ?, ?, ?)";
        List<Object> args = new ArrayList<>();

        jdbcTemplate.update(con -> {
            PreparedStatement pst = con.prepareStatement(query, new String[]{"id"});

            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getSalt());
            pst.setDate(5, new Date(user.getCreatedAt().getMillis()));

            return pst;
        }, keyHolder);

        user.setId((Long) keyHolder.getKey());

        return user;
    }

    @Override
    public User getUserById(Integer id) {
        String query = "SELECT * FROM t_users WHERE id = ?";
        return jdbcTemplate.queryForObject(query, User.class, id);
    }


}
