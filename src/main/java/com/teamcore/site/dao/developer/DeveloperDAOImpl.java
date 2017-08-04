package com.teamcore.site.dao.developer;

import com.teamcore.site.domain.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeveloperDAOImpl implements DeveloperDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Developer getDeveloperById(Integer id) {
        return null;
    }

    @Override
    public Developer getDeveloperByEmail(String email) {
        return null;
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return null;
    }
}
