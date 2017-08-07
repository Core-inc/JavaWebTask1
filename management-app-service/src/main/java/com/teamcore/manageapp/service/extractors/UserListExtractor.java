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
            User user = new User();
            user.setId(resultSet.getInt("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setEmail(resultSet.getString("c_email"));
            user.setPassword(resultSet.getString("c_password"));
            user.setSalt(resultSet.getString("c_salt"));
            user.setCreatedAt(resultSet.getTimestamp("c_created_at").toLocalDateTime());

            Timestamp createdAt = resultSet.getTimestamp("c_updated_at");
            user.setCreatedAt(createdAt != null ? createdAt.toLocalDateTime() : null);

            Role role = new Role();
            role.setId(resultSet.getInt("role_id"));
            role.setRoleId(resultSet.getInt("c_group_id"));
            role.setName(resultSet.getString("role_name"));

            user.setRole(role);

            users.add(user);
        }

        return users;
    }
}
