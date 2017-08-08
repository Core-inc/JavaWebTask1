package com.teamcore.manageapp.service.extractors;

import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExtractor implements ResultSetExtractor<User> {

    @Override
    public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        resultSet.next();

        Role role = new Role();
        role.setId(resultSet.getLong("role_id"));
        role.setRoleId(resultSet.getInt("c_group_id"));
        role.setName(resultSet.getString("role_name"));

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
}
