package com.teamcore.manageapp.service.dao.user;

import com.teamcore.manageapp.service.domain.Skill;
import com.teamcore.manageapp.service.domain.User;
import com.teamcore.manageapp.service.extractors.UserExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final String INSERT_USER = "INSERT INTO t_users " +
            "(c_name, c_email, c_password, c_salt, c_created_at, c_user_group_id) values (?, ?, ?, ?, ?, ?)";

    private static final String GET_USER_BY_ID = "SELECT id, c_name, c_email, c_password, c_salt, c_created_at " +
            "FROM t_users WHERE id = ?";

    private static final String GET_USER_BY_EMAIL = "SELECT id, c_name, c_email, c_password, c_salt, c_created_at " +
            "FROM t_users WHERE c_email = ?";

    private static final String ADD_SKILL_TO_USER = "INSERT INTO t_users_skills (user_id, skill_id) values (?, ?)";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement pst = con.prepareStatement(INSERT_USER, new String[]{"id"});

            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getSalt());
            pst.setTimestamp(5, Timestamp.valueOf(user.getCreatedAt()));
            pst.setInt(6, user.getRoleId());

            return pst;
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());


        return user;
    }

    @Override
    public User getUserById(Long id) {
        return jdbcTemplate.query(GET_USER_BY_ID, new Object[]{id}, new UserExtractor());
    }

    @Override
    public User getUserByEmail(String email) {
        return jdbcTemplate.query(GET_USER_BY_EMAIL, new Object[]{email}, new UserExtractor());

    }

    @Override
    public void addSkillToUser(User user, Skill skill) {
        jdbcTemplate.update(ADD_SKILL_TO_USER, user.getId(), skill.getId());
    }


}
