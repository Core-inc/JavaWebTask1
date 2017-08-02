package com.teamcore.site.extractors;

import com.teamcore.site.domain.Skill;
import com.teamcore.site.domain.User;
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
                user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setName(resultSet.getString("user_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setSalt(resultSet.getString("salt"));
                user.setCreatedAt(resultSet.getDate("created_at"));

            }

            Skill skill = new Skill();

            skill.setId(resultSet.getLong("skill_id"));
            skill.setName(resultSet.getString("skill_name"));

            if (skill.getId() != null) {
                user.addSkill(skill);
            }
        }

        return user;
    }
}
