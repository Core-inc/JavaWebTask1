package com.teamcore.manageapp.service.dao.user;

import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private static final String INSERT_USER = "INSERT INTO t_users " +
            "(c_name, c_email, c_password, c_salt, c_created_at, c_updated_at, c_user_group_id) " +
            "values (:name, :email, :password, :salt, :createdAt, :updatedAt, :roleId)";

    private static final String UPDATE_USER = "UPDATE t_users " +
            "SET c_name = :name, c_email = :email, c_password = :password, c_salt = :salt, " +
            "c_created_at = :createdAt, c_updated_at = :updatedAt, c_user_group_id = :roleId " +
            "WHERE id = :id";

    private static final String GET_USER_BY_ID = "SELECT t_users.id as user_id, t_users.c_name as user_name, c_email, " +
            "c_password, c_salt, c_created_at, c_updated_at,  " +
            "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
            "FROM t_users JOIN t_user_groups on t_users.c_user_group_id = t_user_groups.c_group_id " +
            "WHERE t_users.id = :id";

    private static final String GET_USER_BY_EMAIL = "SELECT t_users.id as user_id, t_users.c_name as user_name, c_email, " +
            "c_password, c_salt, c_created_at, c_updated_at,  " +
            "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
            "FROM t_users JOIN t_user_groups on t_users.c_user_group_id = t_user_groups.c_group_id " +
            "WHERE c_email = :email";

    private static final String DELETE_USER = "DELETE FROM t_users WHERE id = :id";

    private static final String GET_ROLE_BY_USER_ID = "SELECT t_user_groups.id, c_group_id, t_user_groups.c_name " +
            "FROM t_user_groups JOIN t_users on t_user_groups.c_group_id = t_users.c_user_group_id " +
            "WHERE t_users.id = :id";

    private static final String GET_ALL_USERS = "SELECT t_users.id as user_id, t_users.c_name as user_name, c_email, " +
            "c_password, c_salt, c_created_at, c_updated_at,  " +
            "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
            "FROM t_users JOIN t_user_groups on c_user_group_id = c_group_id";

    private static final String GET_ALL_USERS_BY_NAME = "SELECT t_users.id as user_id, t_users.c_name as user_name, c_email, " +
            "c_password, c_salt, c_created_at, c_updated_at,  " +
            "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
            "FROM t_users JOIN t_user_groups on c_user_group_id = c_group_id " +
            "WHERE t_users.c_name = :name";


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getById(Long id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID,
                new MapSqlParameterSource("id", id), UserDAOImpl::userRowMap);
    }

    @Override
    public User save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(INSERT_USER,
                new MapSqlParameterSource()
                    .addValue("name", user.getName())
                    .addValue("email", user.getEmail())
                    .addValue("password", user.getPassword())
                    .addValue("salt", user.getSalt())
                    .addValue("createdAt", Timestamp.valueOf(LocalDateTime.now()))
                    .addValue("updatedAt", Timestamp.valueOf(LocalDateTime.now()))
                    .addValue("roleId", user.getRole().getId()),
                keyHolder, new String[]{"id"});

        user.setId(keyHolder.getKey().longValue());

        return user;
    }

    @Override
    public User update(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(UPDATE_USER,
                new MapSqlParameterSource()
                        .addValue("id", user.getId())
                        .addValue("name", user.getName())
                        .addValue("email", user.getEmail())
                        .addValue("password", user.getPassword())
                        .addValue("salt", user.getSalt())
                        .addValue("createdAt", Timestamp.valueOf(user.getCreatedAt()))
                        .addValue("updatedAt", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("roleId", user.getRole().getId()),
                keyHolder, new String[]{"id"});

        user.setId(keyHolder.getKey().longValue());

        return user;
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject(GET_USER_BY_EMAIL,
                new MapSqlParameterSource("email", email), UserDAOImpl::userRowMap);
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(GET_ALL_USERS, UserDAOImpl::userRowMap);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_USER, new MapSqlParameterSource("id", id));
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update(DELETE_USER, new MapSqlParameterSource("id", user.getId()));
    }

    @Override
    public List<User> getAllByName(String name) {
        return jdbcTemplate.query(GET_ALL_USERS_BY_NAME, new MapSqlParameterSource("name", name), UserDAOImpl::userRowMap);
    }

    @Override
    public Role getRoleByUserId(Long id) {
        return jdbcTemplate.queryForObject(GET_ROLE_BY_USER_ID, new MapSqlParameterSource("id", id), UserDAOImpl::roleRowMap);
    }

    private static User userRowMap(ResultSet resultSet, int i) throws SQLException {
        Role role = Role.newBuilder()
                .setId(resultSet.getLong("role_id"))
                .setRoleId(resultSet.getInt("c_group_id"))
                .setName(resultSet.getString("role_name"))
                .build();

        User user = User.newBuilder()
                .setId(resultSet.getLong("user_id"))
                .setName(resultSet.getString("user_name"))
                .setEmail(resultSet.getString("c_email"))
                .setPassword(resultSet.getString("c_password"))
                .setSalt(resultSet.getString("c_salt"))
                .setCreatedAt(resultSet.getTimestamp("c_created_at")
                        .toLocalDateTime())
                .setUpdatedAt(resultSet.getTimestamp("c_updated_at")
                        .toLocalDateTime())
                .setRole(role)
                .build();

        return user;
    }

    private static Role roleRowMap(ResultSet resultSet, int i) throws SQLException {
        return Role.newBuilder()
                .setId(resultSet.getLong("id"))
                .setRoleId(resultSet.getInt("c_group_id"))
                .setName(resultSet.getString("c_name"))
                .build();
    }
}
