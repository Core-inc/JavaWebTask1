package com.teamcore.site.extractors;

import com.teamcore.site.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserExtractor implements ResultSetExtractor<User> {

    @Override
    public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
               User user = null;

        while (resultSet.next()) {
            if (user == null) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("c_name"));
                user.setEmail(resultSet.getString("c_email"));
                user.setPassword(resultSet.getString("c_password"));
                user.setSalt(resultSet.getString("c_salt"));
                user.setCreatedAt(resultSet.getTimestamp("c_created_at").toLocalDateTime());

            }
        }

        return user;
    }
}
