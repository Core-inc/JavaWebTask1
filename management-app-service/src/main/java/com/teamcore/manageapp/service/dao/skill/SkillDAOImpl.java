package com.teamcore.manageapp.service.dao.skill;

import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SkillDAOImpl implements SkillDAO {

    public final static String SELECT_SKILL_BY_ID = "SELECT * FROM t_skills WHERE id = ?";
    public final static String SELECT_ALL_SKILLS = "SELECT * FROM t_skills ORDER BY id ASC";

    public final static String INSERT_SKILL = "INSERT INTO t_skills (name) values (:name)";
    public final static String UPDATE_SKILL = "UPDATE t_skills SET c_name = :name WHERE id = :id";
    public final static String DELETE_SKILL = "DELETE FROM t_skills WHERE id = :id";

    public final static String SELECT_ALL_DEVELOPERS_BY_SKILL_ID =
            "WITH developer_ids AS " +
                    "(" +
                    "SELECT c_developer_id as id FROM t_developers_skills WHERE c_skill_id = :id" +
                    ") " +
                    "SELECT * FROM t_developers " +
                    "JOIN developer_ids ON (t_developers.c_user_id = developer_ids.id) "+
                    "JOIN t_users ON (t_developers.c_user_id = t_users.id)";

    public final static String SELECT_FREE_DEVELOPERS_BY_SKILL_ID =
            "WITH developer_ids AS "+
                    "("+
                    "SELECT c_developer_id AS id FROM t_developers_skills "+
                    "WHERE c_skill_id = :id AND "+
                    "NOT EXISTS(" +
                    "SELECT NULL FROM t_developers_tasks WHERE "+
                    "t_developers_skills.c_developer_id = t_developers_tasks.c_developer_id)"+
                    ") "+
                    "SELECT * FROM t_developers " +
                    "JOIN developer_ids ON (t_developers.c_user_id = developer_ids.id) "+
                    "JOIN t_users ON (t_developers.c_user.id = t_users.id)";



    public final static String SELECT_PROJECTS_BY_SKILL_ID =
            "WITH task_ids AS "+
                    "(SELECT c_task_id AS id FROM t_tasks_skills WHERE c_skill_id = :id) "+
                    "SELECT * FROM t_projects JOIN task_ids ON (t_projects.id = task_ids.id)";


    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Skill addSkill(Skill skill) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(INSERT_SKILL,
                new MapSqlParameterSource("name", skill.getName()),
                keyHolder);

        skill.setId((Integer)keyHolder.getKey());

        return skill;
    }

    @Override
    public Skill updateSkill(Skill skill) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", skill.getId())
                .addValue("name", skill.getName());
        jdbcTemplate.update(UPDATE_SKILL, namedParameters);

        return skill;
    }

    @Override
    public void deleteSkill(int id) {
        jdbcTemplate.update(DELETE_SKILL, new MapSqlParameterSource("id", id));
    }

    @Override
    public Skill getSkillById(int id) {
        return jdbcTemplate.query(SELECT_SKILL_BY_ID,
                new MapSqlParameterSource("id", id),
                (ResultSet resultSet) -> {
                    Skill skill = null;

                    if(resultSet.next()) {
                        skill = Skill.newBuilder()
                                .setId(resultSet.getInt("id"))
                                .setName(resultSet.getString("c_name"))
                                .build();
                    }

                    return skill;
                });
    }

    @Override
    public List<Skill> getAllSkills() {
        return jdbcTemplate.query(SELECT_ALL_SKILLS,
                (ResultSet resultSet) -> {
                    List<Skill> skillList = new ArrayList<>();

                    while(resultSet.next()) {
                        skillList.add(Skill
                                .newBuilder()
                                .setId(resultSet.getInt("id"))
                                .setName(resultSet.getString("c_name"))
                                .build()
                        );
                    }

                    return skillList;
                });
    }

    @Override
    public List<Developer> getAllDevelopersBySkillId(int id) {
        return jdbcTemplate.query(SELECT_ALL_DEVELOPERS_BY_SKILL_ID,
                new MapSqlParameterSource("id", id),
                new DeveloperListExtractor());
    }

    @Override
    public List<Developer> getFreeDevelopersBySkillId(int id) {
        return jdbcTemplate.query(SELECT_FREE_DEVELOPERS_BY_SKILL_ID,
                new MapSqlParameterSource("id", id),
                new DeveloperListExtractor());
    }

    @Override
    public List<Project> getProjectsBySkillId(int id) {
        return jdbcTemplate.query(SELECT_PROJECTS_BY_SKILL_ID,
                new MapSqlParameterSource("id", id),
                (ResultSet resultSet) -> {
                    List<Project> projectList = new ArrayList<>();

                    while(resultSet.next()) {
                        projectList.add(Project
                                .newBuilder()
                                .setId(resultSet.getInt("t_projects.id"))
                                .setExternalName(resultSet.getString("t_projects.c_exter_name"))
                                .setInternalName(resultSet.getString("t_projects.c_inter_name"))
                                .setSpecLink(resultSet.getString("t_projects.c_specs_link"))
                                .setStatus(resultSet.getInt("t_projects.c_status"))
                                .setCreatedAt(resultSet.getTimestamp("t_projects.c_created_at")
                                        .toLocalDateTime())
                                .setUpdatedAt(resultSet.getTimestamp("t_projects.c_updated_at")
                                        .toLocalDateTime())
                                .build()
                        );
                    }

                    return projectList;
                });
    }

    private static class DeveloperListExtractor implements ResultSetExtractor<List<Developer>> {

        @Override
        public List<Developer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            List<Developer> developerList = new ArrayList<>();

            while(resultSet.next()) {
                developerList.add(Developer
                        .newBuilder()
                        .setId(resultSet.getInt("t_users.id"))
                        .setName(resultSet.getString("t_users.c_name"))
                        .setEmail(resultSet.getString("t_users.c_email"))
                        .setCreatedAt(resultSet.getTimestamp("t_users.c_created_at")
                                .toLocalDateTime())
                        .setUpdatedAt(resultSet.getTimestamp("t_users.c_updated_at")
                                .toLocalDateTime())
                        .build()
                );
            }

            return developerList;
        }
    }

}
