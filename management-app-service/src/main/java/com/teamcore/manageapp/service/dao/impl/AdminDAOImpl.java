package com.teamcore.manageapp.service.dao.impl;

import com.teamcore.manageapp.service.dao.AdminDAO;
import com.teamcore.manageapp.service.domain.Admin;
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


@Repository
public class AdminDAOImpl implements AdminDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;


    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Admin save(Admin user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(INSERT_USER,
                new MapSqlParameterSource()
                        .addValue("name", user.getName())
                        .addValue("email", user.getEmail())
                        .addValue("password", user.getPassword())
                        .addValue("salt", user.getSalt())
                        .addValue("createdAt", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("updatedAt", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("roleId", Role.ADMIN.getRoleId()),
                keyHolder, new String[]{"id"});

        user.setId(keyHolder.getKey().longValue());
        user.setRole(Role.ADMIN);

        return user;
    }

    @Override
    public Admin update(Admin user) {

        jdbcTemplate.update(UPDATE_USER_BY_ROLE,
                new MapSqlParameterSource()
                        .addValue("id", user.getId())
                        .addValue("name", user.getName())
                        .addValue("email", user.getEmail())
                        .addValue("password", user.getPassword())
                        .addValue("salt", user.getSalt())
                        .addValue("createdAt", Timestamp.valueOf(user.getCreatedAt()))
                        .addValue("updatedAt", Timestamp.valueOf(LocalDateTime.now()))
                        .addValue("roleId", Role.ADMIN.getRoleId())
        );

        return user;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_USER_BY_ROLE,
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("roleId", Role.ADMIN.getRoleId()));
    }

    @Override
    public Admin getById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_ID_AND_ROLE,
                new MapSqlParameterSource()
                        .addValue("id", id)
                        .addValue("roleId", Role.ADMIN.getRoleId()),
                AdminDAOImpl::adminRowMap);
    }

    @Override
    public Admin getByEmail(String email) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL_AND_ROLE,
                new MapSqlParameterSource()
                        .addValue("email", email)
                        .addValue("roleId", Role.ADMIN.getRoleId()),
                AdminDAOImpl::adminRowMap);
    }

    @Override
    public List<Admin> getAll() {
        return jdbcTemplate.query(SELECT_ALL_USERS_BY_ROLE,
                new MapSqlParameterSource()
                        .addValue("roleId", Role.ADMIN.getRoleId()),
                AdminDAOImpl::adminRowMap);
    }

    @Override
    public List<Admin> getAllByName(String name) {
        return jdbcTemplate.query(SELECT_ALL_USERS_BY_NAME_AND_ROLE,
                new MapSqlParameterSource()
                        .addValue("name", name)
                        .addValue("roleId", Role.ADMIN.getRoleId()),
                AdminDAOImpl::adminRowMap);
    }

    private static Admin adminRowMap(ResultSet resultSet, int i) throws SQLException {

        return Admin.newBuilder()
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

