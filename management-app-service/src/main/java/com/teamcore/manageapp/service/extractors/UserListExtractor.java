package com.teamcore.manageapp.service.extractors;

import com.teamcore.manageapp.service.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserListExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("c_name"));
            user.setEmail(resultSet.getString("c_email"));
            user.setPassword(resultSet.getString("c_password"));
            user.setSalt(resultSet.getString("c_salt"));
            user.setCreatedAt(resultSet.getTimestamp("c_created_at").toLocalDateTime());
            users.add(user);
        }

        return users;
    }
}
