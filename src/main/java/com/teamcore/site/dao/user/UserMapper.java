package com.teamcore.site.dao.user;

import com.teamcore.site.domain.Skill;
import com.teamcore.site.domain.User;
import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();

        while (resultSet.next()) {
            user.setId(resultSet.getLong("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setSalt(resultSet.getString("salt"));
            user.setCreatedAt(resultSet.getDate("created_at"));

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
