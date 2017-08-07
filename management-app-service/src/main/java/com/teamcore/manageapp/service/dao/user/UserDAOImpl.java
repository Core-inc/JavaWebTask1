package com.teamcore.manageapp.service.dao.user;

import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.Skill;
import com.teamcore.manageapp.service.domain.User;
import com.teamcore.manageapp.service.extractors.RoleExtractor;
import com.teamcore.manageapp.service.extractors.UserExtractor;
import com.teamcore.manageapp.service.extractors.UserListExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final String INSERT_USER = "INSERT INTO t_users " +
            "(c_name, c_email, c_password, c_salt, c_created_at, c_user_group_id) values (?, ?, ?, ?, ?, ?)";

    private static final String GET_USER_BY_ID = "SELECT id, c_name, c_email, c_password, c_salt, c_created_at " +
            "FROM t_users WHERE id = ?";

    private static final String GET_USER_BY_EMAIL = "SELECT id, c_name, c_email, c_password, c_salt, c_created_at " +
            "FROM t_users WHERE c_email = ?";

    private static final String ADD_SKILL_TO_USER = "INSERT INTO t_users_skills (user_id, skill_id) values (?, ?)";

    private static final String DELETE_USER = "DELETE FROM t_users WHERE id = ?";

    private static final String GET_ROLE_BY_USER_ID = "SELECT id, c_group_id, c_name, FROM t_user_groups " +
            "JOIN t_users on t_users.c_user_group_id = t_users_groups.c_group_id WHERE t_users.id = ?";

    private static final String GET_ALL_USERS = "SELECT * FROM t_users";

    private static final String GET_ALL_USERS_BY_NAME = "SELECT * FROM t_users WHERE c_name = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User getById(Integer id) {
        return jdbcTemplate.query(GET_USER_BY_ID, new Object[]{id}, new UserExtractor());
    }

    @Override
    public User save(User user) {
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
    public User getByEmail(String email) {
        return jdbcTemplate.query(GET_USER_BY_EMAIL, new Object[]{email}, new UserExtractor());
    }

    @Override
    public void addSkill(User user, Skill skill) {
        jdbcTemplate.update(ADD_SKILL_TO_USER, user.getId(), skill.getId());
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(GET_ALL_USERS, new UserListExtractor());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_USER, id);
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update(DELETE_USER, user.getId());
    }

    @Override
    public List<User> getAllByName(String name) {
        return jdbcTemplate.query(GET_ALL_USERS_BY_NAME, new UserListExtractor());
    }

    @Override
    public Role getRoleByUserId(Integer id) {
        return jdbcTemplate.query(GET_ROLE_BY_USER_ID, new Object[]{id}, new RoleExtractor());
    }
}
