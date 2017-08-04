package com.teamcore.manageapp.service.dao.skill;

import com.teamcore.manageapp.service.domain.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class SkillDAOImpl implements SkillDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Skill addSkill(Skill skill) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String query = "INSERT INTO t_skills (name) values (?)";

        jdbcTemplate.update(con -> {
            PreparedStatement pst = con.prepareStatement(query, new String[]{"id"});

            pst.setString(1, skill.getName());

            return pst;
        }, keyHolder);

        skill.setId((Integer)keyHolder.getKey());

        return skill;
    }

    @Override
    public Skill getSkillById(Long id) {
        String query = "SELECT * FROM t_skills WHERE id = ?";
        return jdbcTemplate.queryForObject(query, Skill.class, id);
    }
}
