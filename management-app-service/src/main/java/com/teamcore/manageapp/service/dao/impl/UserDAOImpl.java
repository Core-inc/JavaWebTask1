package com.teamcore.manageapp.service.dao.impl;

import com.teamcore.manageapp.service.dao.UserDAO;
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

/**
 * {@see UserDAO} Implementation based on {@see NamedParameterJdbcTemplate} class
 * for retrieving {@see User} objects that represent users
 * in our system
 */
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

    private static final String GET_ALL_CUSTOMERS = "SELECT t_users.id as user_id, t_users.c_name as user_name, c_email, " +
            "c_password, c_salt, c_created_at, c_updated_at,  " +
            "t_user_groups.id as role_id, c_group_id, t_user_groups.c_name as role_name " +
            "FROM t_users JOIN t_user_groups on c_user_group_id = c_group_id " +
            "WHERE c_user_group_id = :roleId";

    private static final String ADD_CUSTOMER_TO_PROJECT = "INSERT INTO t_customers_projects " +
            "(c_customer_id, c_project_id) VALUES (:customerId, :projectId)";

    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * setter injection method to setup {@see NamedParameterJdbcTemplate} object
     * @param jdbcTemplate {@see NamedParameterJdbcTemplate} object to inject
     */
    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * saves specified {@see User} object in database
     * @param user {@see User} object to save in database
     * @return saved {@see User} object
     * @see User
     */
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
                        .addValue("roleId", user.getRole().getRoleId()),
                keyHolder, new String[]{"id"});

        user.setId(keyHolder.getKey().longValue());

        return user;
    }


    /**
     * updates specified {@see User} object in database
     * @param user {@see User} object to update in database
     * @return updated {@see User} object
     * @see User
     */
    @Override
    public User update(User user) {

        jdbcTemplate.update(UPDATE_USER,
                new MapSqlParameterSource()
                        .addValue("id", user.getId())
                        .addValue("name", user.getName())
                        .addValue("email", user.getEmail())
                        .addValue("password", user.getPassword())
                        .addValue("salt", user.getSalt())
                        .addValue("createdAt", Timestamp.valueOf(user.getCreatedAt()))
                        .addValue("updatedAt", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("roleId", user.getRole().getRoleId())
        );

        return user;
    }

    /**
     * deletes user with specified {@code id} from database
     * @param id id of the user to delete from database
     */
    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_USER, new MapSqlParameterSource("id", id));
    }

    /**
     * retrieves user with specific {@code id} from database
     * @param id id of the user in database
     * @return {@see User} object describing requested user entity
     * @see User
     */
    @Override
    public User getById(Long id) {
        return jdbcTemplate.queryForObject(GET_USER_BY_ID,
                new MapSqlParameterSource("id", id), UserDAOImpl::userRowMap);
    }


    /**
     * retrieves user with specific {@code email} from database
     * @param email email of user in database
     * @return {@see User} object describing requested user entity
     * @see User
     */
    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject(GET_USER_BY_EMAIL,
                new MapSqlParameterSource("email", email), UserDAOImpl::userRowMap);
    }

    /**
     * retrieves list of all users in system
     * @return list of all users in system
     * @see User
     */
    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(GET_ALL_USERS, UserDAOImpl::userRowMap);
    }


    /**
     * retrieves list of all users in system with specified {@code name}
     * @return list of all users in system with specified {@code name}
     * @see User
     */
    @Override
    public List<User> getAllByName(String name) {
        return jdbcTemplate.query(GET_ALL_USERS_BY_NAME, new MapSqlParameterSource("name", name), UserDAOImpl::userRowMap);
    }

    /**
     * returns {@see Role} enum for user entity with specific {@code id}
     * @param id id of the user in database
     * @see Role
     */
    @Override
    public Role getRoleByUserId(Long id) {
        return jdbcTemplate.queryForObject(GET_ROLE_BY_USER_ID, new MapSqlParameterSource("id", id), UserDAOImpl::roleRowMap);
    }

    @Override
    public List<User> getAllCustomers() {
        return jdbcTemplate.query(GET_ALL_CUSTOMERS,
                new MapSqlParameterSource()
                        .addValue("roleId", Role.CUSTOMER.getRoleId()), UserDAOImpl::userRowMap);
    }

    @Override
    public void addCustomerProject(Long customerId, Long projectId) {
        jdbcTemplate.update(ADD_CUSTOMER_TO_PROJECT,
                new MapSqlParameterSource()
                        .addValue("customerId", customerId)
                        .addValue("projectId", projectId));
    }

    /**
     * static method {@see RowMapper} interface implementation
     * for {@see User} object
     */
    public static User userRowMap(ResultSet resultSet, int i) throws SQLException {
        Role role = Role.getRoleByRoleId(resultSet.getInt("c_group_id"));

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

    /**
     * static method {@see RowMapper} interface implementation
     * for {@see Role} enum
     */
    public static Role roleRowMap(ResultSet resultSet, int i) throws SQLException {
        return Role.getRoleByRoleId(resultSet.getInt("c_group_id"));
    }
}
