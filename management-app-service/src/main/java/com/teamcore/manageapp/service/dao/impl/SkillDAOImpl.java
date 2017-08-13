package com.teamcore.manageapp.service.dao.impl;

import com.teamcore.manageapp.service.dao.SkillDAO;
import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * {@see SkillDao} interface implementation for general operations
 * on {@see Skill} data-access objects
 */
@Repository
public class SkillDAOImpl implements SkillDAO {

    public final static String SELECT_SKILL_BY_ID = "SELECT * FROM t_skills WHERE id = :id";
    public final static String SELECT_ALL_SKILLS = "SELECT * FROM t_skills ORDER BY id ASC";

    public final static String INSERT_SKILL = "INSERT INTO t_skills (c_name) values (:name)";
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

    /**
     * setter for NamedParameterJdbcTemplate injection
     * @param jdbcTemplate - injected realization of{@see NamedParameterJdbcTemplate} interface
     */
    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * save {@see Skill} object in database
     * @param skill - {@see Skill} object which will be saved
     * @return saved {@see Skill} object with new {@code id} from database
     */
    @Override
    public Skill addSkill(Skill skill) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(INSERT_SKILL,
                new MapSqlParameterSource("name", skill.getName()),
                keyHolder, new String[]{"id"});

        skill.setId(keyHolder.getKey().longValue());

        return skill;
    }

    /**
     * update existing {@see Skill} object in database
     * @param skill - {@see Skill} object which will be updated
     * @return updated {@see Skill} object
     */
    @Override
    public Skill updateSkill(Skill skill) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", skill.getId())
                .addValue("name", skill.getName());
        jdbcTemplate.update(UPDATE_SKILL, namedParameters);

        return skill;
    }

    /**
     * delete existing {@see Skill} object from database
     * @param id - {@see Skill} {@code id} in database
     */
    @Override
    public void deleteSkill(Long id) {
        jdbcTemplate.update(DELETE_SKILL, new MapSqlParameterSource("id", id));
    }

    /**
     * retrieves {@see Skill} object with specific {@code id} in database
     * @param id - {@see Skill} {@code id} in database
     * @return {@see Skill} object with specific {@code id} from database
     */
    @Override
    public Skill getSkillById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_SKILL_BY_ID,
                new MapSqlParameterSource("id", id),
                (ResultSet resultSet, int i) -> Skill
                        .newBuilder()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("c_name"))
                        .build()
        );
    }

    /**
     * retrieves list of all {@see Skill} objects from database
     * @return list of {@see Skill} objects from database
     */
    @Override
    public List<Skill> getAllSkills() {
        return jdbcTemplate.query(SELECT_ALL_SKILLS,
                (ResultSet resultSet, int i) -> Skill
                        .newBuilder()
                        .setId(resultSet.getLong("id"))
                        .setName(resultSet.getString("c_name"))
                        .build()
        );
    }

    /**
     * retrieves list of all {@see Developer} objects which have {@see Skill}
     * specified by {@code id}
     * @param id - {@see Skill} {@code id} in database
     * @return list of {@see Developer} objects with specific {@see Skill}
     * from database
     */
    @Override
    public List<Developer> getAllDevelopersBySkillId(Long id) {
        return jdbcTemplate.query(SELECT_ALL_DEVELOPERS_BY_SKILL_ID,
                new MapSqlParameterSource("id", id),
                SkillDAOImpl::developerRowMap);
    }

    /**
     * retrieves list of all {@see Developer} objects with "free" status which have {@see Skill}
     * specified by {@code id}
     * @param id - {@see Skill} {@code id} in database
     * @return list of {@see Developer} objects with specific {@see Skill}
     * from database
     */
    @Override
    public List<Developer> getFreeDevelopersBySkillId(Long id) {
        return jdbcTemplate.query(SELECT_FREE_DEVELOPERS_BY_SKILL_ID,
                new MapSqlParameterSource("id", id),
                SkillDAOImpl::developerRowMap);
    }

    /**
     * retrieves list of all {@see Project} objects which have {@see Skill}
     * specified by {@code id}
     * @param id - {@see Skill} {@code id} in database
     * @return list of {@see Project} objects with specific {@see Skill}
     * from database
     */
    @Override
    public List<Project> getProjectsBySkillId(Long id) {
        return jdbcTemplate.query(SELECT_PROJECTS_BY_SKILL_ID,
                new MapSqlParameterSource("id", id),
                (ResultSet resultSet, int i) -> Project
                        .newBuilder()
                        .setId(resultSet.getLong("t_projects.id"))
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

    /**
     * RowMapper interface implementation for mapping ResultSet to {@see Developer} object
     */
    private static Developer developerRowMap(ResultSet resultSet, int i) throws SQLException {
        return Developer
                .newBuilder()
                .setId(resultSet.getLong("t_users.id"))
                .setName(resultSet.getString("t_users.c_name"))
                .setEmail(resultSet.getString("t_users.c_email"))
                .setCreatedAt(resultSet.getTimestamp("t_users.c_created_at")
                        .toLocalDateTime())
                .setUpdatedAt(resultSet.getTimestamp("t_users.c_updated_at")
                        .toLocalDateTime())
                .build();
    }

}
