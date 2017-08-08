package com.teamcore.manageapp.service.extractors;

import com.teamcore.manageapp.service.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExtractor implements ResultSetExtractor<User> {

    @Override
    public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        User user = null;

        while (resultSet.next()) {
            if (user == null) {
                user = User.newBuilder()
                        .setId(resultSet.getInt("id"))
                        .setName(resultSet.getString("c_name"))
                        .setEmail(resultSet.getString("c_email"))
                        .setPassword(resultSet.getString("c_password"))
                        .setSalt(resultSet.getString("c_salt"))
                        .setCreatedAt(resultSet.getTimestamp("c_created_at")
                                .toLocalDateTime())
                        .build();


            }
        }

        return user;
    }
}
