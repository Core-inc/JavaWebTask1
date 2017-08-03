package com.teamcore.site.dao.user;

import com.teamcore.site.domain.Skill;
import com.teamcore.site.domain.User;
import com.teamcore.site.extractors.DeveloperExtractor;
import com.teamcore.site.extractors.UserExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String query = "INSERT INTO t_users (c_name, c_email, c_password, c_salt, c_created_at, c_user_group_id) values (?, ?, ?, ?, ?, ?)";
        List<Object> args = new ArrayList<>();

        jdbcTemplate.update(con -> {
            PreparedStatement pst = con.prepareStatement(query, new String[]{"id"});

            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getSalt());
            pst.setTimestamp(5, Timestamp.valueOf(user.getCreatedAt()));
            pst.setInt(6, user.getRoleId());

            return pst;
        }, keyHolder);

        user.setId((Integer)keyHolder.getKey());


        return user;
    }

    @Override
    public User getUserById(Integer id) {
        String query = "SELECT id, c_name, c_email, c_password, c_salt, c_created_at " +
                "FROM t_users WHERE id = ?";


        return jdbcTemplate.query(query, new Object[]{id}, new UserExtractor());
    }

    @Override
    public User getUserByEmail(String email) {
        String query = "SELECT id, c_name, c_email, c_password, c_salt, c_created_at " +
                "FROM t_users WHERE c_email = ?";

        return jdbcTemplate.query(query, new Object[]{email}, new UserExtractor());

    }

    @Override
    public void addSkillToUser(User user, Skill skill) {
        String query = "INSERT INTO t_users_skills (user_id, skill_id) values (?, ?)";
        jdbcTemplate.update(query, user.getId(), skill.getId());
    }


}
