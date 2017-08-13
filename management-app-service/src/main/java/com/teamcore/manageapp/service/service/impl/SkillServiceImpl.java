package com.teamcore.manageapp.service.service.impl;

import com.teamcore.manageapp.service.dao.SkillDAO;
import com.teamcore.manageapp.service.domain.Skill;
import com.teamcore.manageapp.service.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@see SkillService} implementation based on {@see SkillDao} interface
 * for retrieving {@see Skill} objects from database
 * that represents skill of the {@see Developer}
 */
@Service
public class SkillServiceImpl implements SkillService {
    private SkillDAO skillDAO;

    /**
     * setter for {@see SkillDao} injection
     * @param skillDAO - injected realization of{@see SkillDao} interface
     *                 for interactions with database
     */
    @Autowired
    public void setSkillDAO(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }

    /**
     * retrieves list of all skills from database
     * @return list of {@see Skill} objects from database
     */
    @Override
    public List<Skill> getAll() {
        return skillDAO.getAllSkills();
    }

    /**
     * retrieves {@see Skill} object with specific {@code id} in database
     * @param id - {@see Skill} {@code id} in database
     * @return {@see Skill} object with specific {@code id} from database
     */
    @Override
    public Skill getById(Long id) {
        return skillDAO.getSkillById(id);
    }

    /**
     * save {@see Skill} object in database
     * @param domainObject - {@see Skill} object which will be saved
     * @return saved {@see Skill} object with new {@code id} from database
     */
    @Override
    public Skill save(Skill domainObject) {
        return skillDAO.addSkill(domainObject);
    }

    /**
     * update existing {@see Skill} object in database
     * @param domainObject - {@see Skill} object which will be updated
     * @return updated {@see Skill} object
     */
    @Override
    public Skill update(Skill domainObject) {
        return skillDAO.updateSkill(domainObject);
    }

    /**
     * delete existing {@see Skill} object from database
     * @param id - {@see Skill} {@code id} in database
     */
    @Override
    public void delete(Long id) {
        skillDAO.deleteSkill(id);
    }
}
