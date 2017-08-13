package com.teamcore.manageapp.service.dao;


import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Skill;

import java.util.List;

/**
 * An interface which describes general operations
 * on {@see Skill} data-access objects
 */
public interface SkillDAO {
    /**
     * save {@see Skill} object in database
     * @param skill - {@see Skill} object which will be saved
     * @return saved {@see Skill} object with new {@code id} from database
     */
    Skill addSkill(Skill skill);

    /**
     * update existing {@see Skill} object in database
     * @param skill - {@see Skill} object which will be updated
     * @return updated {@see Skill} object
     */
    Skill updateSkill(Skill skill);

    /**
     * delete existing {@see Skill} object from database
     * @param id - {@see Skill} {@code id} in database
     */
    void deleteSkill(Long id);

    /**
     * retrieves {@see Skill} object with specific {@code id} in database
     * @param id - {@see Skill} {@code id} in database
     * @return {@see Skill} object with specific {@code id} from database
     */
    Skill getSkillById(Long id);

    /**
     * retrieves list of all {@see Skill} objects from database
     * @return list of {@see Skill} objects from database
     */
    List<Skill> getAllSkills();

    /**
     * retrieves list of all {@see Developer} objects which have {@see Skill}
     * specified by {@code id}
     * @param id - {@see Skill} {@code id} in database
     * @return list of {@see Developer} objects with specific {@see Skill}
     * from database
     */
    List<Developer> getAllDevelopersBySkillId(Long id);

    /**
     * retrieves list of all {@see Developer} objects with "free" status which have {@see Skill}
     * specified by {@code id}
     * @param id - {@see Skill} {@code id} in database
     * @return list of {@see Developer} objects with specific {@see Skill}
     * from database
     */
    List<Developer> getFreeDevelopersBySkillId(Long id);

    /**
     * retrieves list of all {@see Project} objects which have {@see Skill}
     * specified by {@code id}
     * @param id - {@see Skill} {@code id} in database
     * @return list of {@see Project} objects with specific {@see Skill}
     * from database
     */
    List<Project> getProjectsBySkillId(Long id);

    Skill getSkillByDeveloperId(Long id);
}
