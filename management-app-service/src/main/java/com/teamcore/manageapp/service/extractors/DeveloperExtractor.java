package com.teamcore.manageapp.service.extractors;

import com.teamcore.manageapp.service.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeveloperExtractor implements ResultSetExtractor<User> {
    @Override
    public User extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        User user = null;

        while (resultSet.next()) {
            if (user == null) {
                user = User.newBuilder()
                        .setId(resultSet.getLong("user_id"))
                        .setName(resultSet.getString("user_name"))
                        .setEmail(resultSet.getString("email"))
                        .setPassword(resultSet.getString("password"))
                        .setSalt(resultSet.getString("salt"))
                        .setCreatedAt(resultSet.getTimestamp("created_at")
                                .toLocalDateTime())
                        .build();

            }

//            Skill skill = new Skill();
//
//            skill.setId(resultSet.getLong("skill_id"));
//            skill.setName(resultSet.getString("skill_name"));
//
//            if (skill.getId() != null) {
//                user.addSkill(skill);
//            }
        }

        return user;
    }
}
