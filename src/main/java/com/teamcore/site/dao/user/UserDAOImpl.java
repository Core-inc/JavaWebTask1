package com.teamcore.site.dao.user;

import com.teamcore.site.domain.Skill;
import com.teamcore.site.domain.User;
import com.teamcore.site.extractors.UserExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public User addUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String query = "INSERT INTO t_users (name, email, password, salt, created_at) values (?, ?, ?, ?, ?)";
        List<Object> args = new ArrayList<>();

        jdbcTemplate.update(con -> {
            PreparedStatement pst = con.prepareStatement(query, new String[]{"id"});

            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getSalt());
            pst.setDate(5, user.getCreatedAt());

            return pst;
        }, keyHolder);

        user.setId((Long) keyHolder.getKey());

        if (!user.getSkills().isEmpty()) {
            user.getSkills().forEach(skill -> addSkillToUser(user, skill));
        }

        return user;
    }

    @Override
    public User getUserById(Long id) {
        String query = "SELECT t_users.id AS user_id, t_users.name AS user_name, email, password, salt, created_at, " +
                "t_skills.id AS skill_id, t_skills.name AS skill_name " +
                "FROM t_users " +
                "LEFT JOIN t_users_skills on t_users.id = user_id " +
                "LEFT JOIN t_skills on skill_id = t_users_skills.skill_id " +
                "WHERE user_id = ?";

//        List<User> users = jdbcTemplate.query(query,
//                new PreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                        preparedStatement.setLong(1, id);
//                    }
//                }, new UserMapper());
//
//        return users.get(0);

        return jdbcTemplate.query(query, new Object[]{id}, new UserExtractor());
//
//
//        return jdbcTemplate.queryForObject(query, new Object[]{id}, new UserMapper());
    }

    @Override
    public void addSkillToUser(User user, Skill skill) {
        String query = "INSERT INTO t_users_skills (user_id, skill_id) values (?, ?)";
        jdbcTemplate.update(query, user.getId(), skill.getId());
    }


}
