package com.teamcore.manageapp.service.dao.impl;

import com.teamcore.manageapp.service.dao.DeveloperDAO;
import com.teamcore.manageapp.service.domain.Developer;
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
 * {@see DeveloperDAO} Implementation based on {@see NamedParameterJdbcTemplate} class
 * for retrieving {@see Developer} objects that represent developer users
 * in our system
 */
@Repository
public class DeveloperDAOImpl implements DeveloperDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;


    /**
     * setter injection method to setup {@see NamedParameterJdbcTemplate} object
     * @param jdbcTemplate {@see NamedParameterJdbcTemplate} object to inject
     */
    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


//    @Override
//    public List<Developer> getAllBySkillId(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<Developer> getFreeBySkillId(Long id) {
//        return null;
//    }

    /**
     * saves specified {@see Developer} object in database
     * @param user {@see Developer} object to save in database
     * @return saved {@see Developer} object
     * @see Developer
     */
    @Override
    public Developer save(Developer user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(INSERT_USER,
                new MapSqlParameterSource()
                        .addValue("name", user.getName())
                        .addValue("email", user.getEmail())
                        .addValue("password", user.getPassword())
                        .addValue("salt", user.getSalt())
                        .addValue("createdAt", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("updatedAt", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("roleId", Role.DEVELOPER.getRoleId()),
                keyHolder, new String[]{"id"});

        user.setId(keyHolder.getKey().longValue());
        user.setRole(Role.DEVELOPER);

        return user;
    }

    /**
     * updates specified {@see Developer} object in database
     * @param user {@see Developer} object to update in database
     * @return updated {@see Developer} object
     * @see Developer
     */
    @Override
    public Developer update(Developer user) {

        jdbcTemplate.update(UPDATE_USER_BY_ROLE,
                new MapSqlParameterSource()
                        .addValue("id", user.getId())
                        .addValue("name", user.getName())
                        .addValue("email", user.getEmail())
                        .addValue("password", user.getPassword())
                        .addValue("salt", user.getSalt())
                        .addValue("createdAt", Timestamp.valueOf(user.getCreatedAt()))
                        .addValue("updatedAt", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("roleId", Role.DEVELOPER.getRoleId())
        );

        return user;
    }

    /**
     * deletes developer user with specified {@code id} from database
     * @param id id of the developer user to delete from database
     */
    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_USER_BY_ROLE,
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("roleId", Role.DEVELOPER.getRoleId()));
    }

    /**
     * retrieves developer user with specific {@code id} from database
     * @param id id of developer user in database
     * @return {@see Developer} object describing requested developer user entity
     * @see Developer
     */
    @Override
    public Developer getById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID_AND_ROLE,
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("roleId", Role.DEVELOPER.getRoleId()),
                DeveloperDAOImpl::developerRowMap);
    }

    /**
     * retrieves admin user with specific {@code email} from database
     * @param email email of developer user in database
     * @return {@see Developer} object describing requested developer user entity
     * @see Developer
     */
    @Override
    public Developer getByEmail(String email) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL_AND_ROLE,
                new MapSqlParameterSource()
                        .addValue("email", email)
                        .addValue("roleId", Role.DEVELOPER.getRoleId()),
                DeveloperDAOImpl::developerRowMap);
    }

    /**
     * retrieves list of all developer users in system
     * @return list of all developer users in system
     * @see Developer
     */
    @Override
    public List<Developer> getAll() {
        return jdbcTemplate.query(SELECT_ALL_USERS_BY_ROLE,
                new MapSqlParameterSource()
                        .addValue("roleId", Role.DEVELOPER.getRoleId()),
                DeveloperDAOImpl::developerRowMap);
    }

    /**
     * retrieves list of all developer users in system with specified {@code name}
     * @return list of all developer users in system with specified {@code name}
     * @see Developer
     */
    @Override
    public List<Developer> getAllByName(String name) {
        return jdbcTemplate.query(SELECT_ALL_USERS_BY_NAME_AND_ROLE,
                new MapSqlParameterSource()
                        .addValue("name", name)
                        .addValue("roleId", Role.DEVELOPER.getRoleId()),
                DeveloperDAOImpl::developerRowMap);
    }

    /**
     * static method {@see RowMapper} interface implementation
     * for {@see Developer} object
     */
    private static Developer developerRowMap(ResultSet resultSet, int i) throws SQLException {

        Developer developer = Developer.newBuilder()
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

        return developer;
    }
}
