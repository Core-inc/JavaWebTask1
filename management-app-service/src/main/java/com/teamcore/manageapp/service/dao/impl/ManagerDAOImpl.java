package com.teamcore.manageapp.service.dao.impl;

import com.teamcore.manageapp.service.dao.ManagerDAO;
import com.teamcore.manageapp.service.domain.Manager;
import com.teamcore.manageapp.service.domain.Role;
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

import static com.teamcore.manageapp.service.dao.impl.QueryUserConstant.*;



/**
 * {@see ManagerDAO} Implementation based on {@see NamedParameterJdbcTemplate} class
 * for retrieving {@see Manager} objects that represent manager users
 * in our system
 */
@Repository
public class ManagerDAOImpl implements ManagerDAO {

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
     * saves specified {@see Manager} object in database
     * @param user {@see Manager} object to save in database
     * @return saved {@see Manager} object
     * @see Manager
     */
    @Override
    public Manager save(Manager user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(INSERT_USER,
                new MapSqlParameterSource()
                        .addValue("name", user.getName())
                        .addValue("email", user.getEmail())
                        .addValue("password", user.getPassword())
                        .addValue("salt", user.getSalt())
                        .addValue("createdAt", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("updatedAt", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("roleId", Role.MANAGER.getRoleId()),
                keyHolder, new String[]{"id"});

        user.setId(keyHolder.getKey().longValue());
        user.setRole(Role.MANAGER);

        return user;
    }

    /**
     * updates specified {@see Manager} object in database
     * @param user {@see Manager} object to update in database
     * @return updated {@see Manager} object
     * @see Manager
     */
    @Override
    public Manager update(Manager user) {

        jdbcTemplate.update(UPDATE_USER_BY_ROLE,
                new MapSqlParameterSource()
                        .addValue("id", user.getId())
                        .addValue("name", user.getName())
                        .addValue("email", user.getEmail())
                        .addValue("password", user.getPassword())
                        .addValue("salt", user.getSalt())
                        .addValue("createdAt", Timestamp.valueOf(user.getCreatedAt()))
                        .addValue("updatedAt", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("roleId", Role.MANAGER.getRoleId())
        );

        return user;
    }

    /**
     * deletes manager user with specified {@code id} from database
     * @param id id of the manager user to delete from database
     */
    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_USER_BY_ROLE,
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("roleId", Role.MANAGER.getRoleId()));
    }

    /**
     * retrieves manager user with specific {@code id} from database
     * @param id id of manager user in database
     * @return {@see Manager} object describing requested manager user entity
     * @see Manager
     */
    @Override
    public Manager getById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID_AND_ROLE,
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("roleId", Role.MANAGER.getRoleId()),
                ManagerDAOImpl::managerRowMap);
    }

    /**
     * retrieves manager user with specific {@code email} from database
     * @param email email of manager user in database
     * @return {@see Manager} object describing requested manager user entity
     * @see Manager
     */
    @Override
    public Manager getByEmail(String email) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL_AND_ROLE,
                new MapSqlParameterSource()
                        .addValue("email", email)
                        .addValue("roleId", Role.MANAGER.getRoleId()),
                ManagerDAOImpl::managerRowMap);
    }

    /**
     * retrieves list of all manager users in system
     * @return list of all manager users in system
     * @see Manager
     */
    @Override
    public List<Manager> getAll() {
        return jdbcTemplate.query(SELECT_ALL_USERS_BY_ROLE,
                new MapSqlParameterSource()
                        .addValue("roleId", Role.MANAGER.getRoleId()),
                ManagerDAOImpl::managerRowMap);
    }

    /**
     * retrieves list of all manager users in system with specified {@code name}
     * @return list of all manager users in system with specified {@code name}
     * @see Manager
     */
    @Override
    public List<Manager> getAllByName(String name) {
        return jdbcTemplate.query(SELECT_ALL_USERS_BY_NAME_AND_ROLE,
                new MapSqlParameterSource()
                        .addValue("name", name)
                        .addValue("roleId", Role.MANAGER.getRoleId()),
                ManagerDAOImpl::managerRowMap);
    }

    /**
     * static method {@see RowMapper} interface implementation
     * for {@see Manager} object
     */
    private static Manager managerRowMap(ResultSet resultSet, int i) throws SQLException {

        return Manager.newBuilder()
                .setId(resultSet.getLong("user_id"))
                .setName(resultSet.getString("user_name"))
                .setEmail(resultSet.getString("c_email"))
                .setPassword(resultSet.getString("c_password"))
                .setSalt(resultSet.getString("c_salt"))
                .setCreatedAt(resultSet.getTimestamp("c_created_at")
                        .toLocalDateTime())
                .setUpdatedAt(resultSet.getTimestamp("c_updated_at")
                        .toLocalDateTime())
                .build();

    }
}


