package com.teamcore.manageapp.service.extractors;

import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserListExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
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

            users.add(user);
        }

        return users;
    }
}
