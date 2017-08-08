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
            "(c_name, c_email, c_password, c_salt, c_created_at, c_updated_at, c_user_group_id) values (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_USER = "UPDATE t_users " +
            "SET c_name = ?, c_email = ?, c_password = ?, c_salt = ?, c_created_at = ?, c_updated_at = ?, c_user_group_id = ? " +
            "WHERE id = ?";

    private static final String GET_USER_BY_ID = "SELECT t_users.id as user_id, t_users.c_name as user_name, c_email, " +
            "c_password, c_salt, c_created_at, c_updated_at,  " +
            "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
            "FROM t_users JOIN t_user_groups on t_users.c_user_group_id = t_user_groups.c_group_id " +
            "WHERE t_users.id = ?";

    private static final String GET_USER_BY_EMAIL = "SELECT t_users.id as user_id, t_users.c_name as user_name, c_email, " +
            "c_password, c_salt, c_created_at, c_updated_at,  " +
            "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
            "FROM t_users JOIN t_user_groups on t_users.c_user_group_id = t_user_groups.c_group_id " +
            "WHERE c_email = ?";

    private static final String ADD_SKILL_TO_USER = "INSERT INTO t_developers_skills (c_developer_id, c_skill_id) values (?, ?)";

    private static final String DELETE_USER = "DELETE FROM t_users WHERE id = ?";

    private static final String GET_ROLE_BY_USER_ID = "SELECT t_user_groups.id, c_group_id, t_user_groups.c_name " +
            "FROM t_user_groups JOIN t_users on t_user_groups.c_group_id = t_users.c_user_group_id " +
            "WHERE t_users.id = ?";

    private static final String GET_ALL_USERS = "SELECT t_users.id as user_id, t_users.c_name as user_name, c_email, " +
            "c_password, c_salt, c_created_at, c_updated_at,  " +
            "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
            "FROM t_users JOIN t_user_groups on c_user_group_id = c_group_id";

    private static final String GET_ALL_USERS_BY_NAME = "SELECT t_users.id as user_id, t_users.c_name as user_name, c_email, " +
            "c_password, c_salt, c_created_at, c_updated_at,  " +
            "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
            "FROM t_users JOIN t_user_groups on c_user_group_id = c_group_id " +
            "WHERE t_users.c_name = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getById(Integer id) {
        return jdbcTemplate.query(GET_USER_BY_ID, new Object[]{id}, new UserExtractor());
    }

    @Override
    public User saveOrUpdate(User user) {
        User resultUser;

        if (user.getId() == null) {
            resultUser = save(user);
        } else {
            resultUser = update(user);
        }

        return resultUser;
    }

    private User save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement pst = con.prepareStatement(INSERT_USER, new String[]{"id"});

            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getSalt());
            pst.setTimestamp(5, Timestamp.valueOf(user.getCreatedAt()));
            pst.setTimestamp(6, user.getUpdatedAt() != null ?
                    Timestamp.valueOf(user.getUpdatedAt()) : null);
            pst.setLong(7, user.getRole().getId());

            return pst;
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());

        return user;
    }

    private User update(User user) {
        jdbcTemplate.update(con -> {
            PreparedStatement pst = con.prepareStatement(UPDATE_USER, new String[]{"id"});

            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getSalt());
            pst.setTimestamp(5, Timestamp.valueOf(user.getCreatedAt()));
            pst.setTimestamp(6, user.getUpdatedAt() != null ?
                    Timestamp.valueOf(user.getUpdatedAt()) : null);
            pst.setLong(7, user.getRole().getId());
            pst.setLong(8, user.getId());

            return pst;
        });

        return user;
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.query(GET_USER_BY_EMAIL, new Object[]{email}, new UserExtractor());
    }

    //TODO move to developer
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
        return jdbcTemplate.query(GET_ALL_USERS_BY_NAME, new Object[]{name}, new UserListExtractor());
    }

    @Override
    public Role getRoleByUserId(Integer id) {
        return jdbcTemplate.query(GET_ROLE_BY_USER_ID, new Object[]{id}, new RoleExtractor());
    }
}
