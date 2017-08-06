package com.teamcore.manageapp.web.controllers.rest;

import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Skill;
import com.teamcore.manageapp.service.services.skill.SkillService;
import com.teamcore.manageapp.service.services.skill.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/skills", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SkillController {

    private SkillService skillService;

    private final static Map<Class<? extends SkillServiceException>, Integer> errorCodeMapper;

    static {
        Map<Class<? extends SkillServiceException>, Integer> codeMapper = new HashMap<>();
        codeMapper.put(SkillServiceException.class, RestError.GENERAL_REST_ERROR);
        codeMapper.put(SkillNotFoundException.class, RestError.SKILL_NOT_FOUND_CODE);
        codeMapper.put(SkillsNotFoundException.class, RestError.SKILLS_NOT_FOUND_CODE);
        codeMapper.put(SkillDevelopersNotFoundException.class, RestError.SKILL_DEVELOPERS_NOT_FOUND_CODE);
        codeMapper.put(SkillProjectsNotFoundException.class, RestError.SKILL_PROJECTS_NOT_FOUND);

        errorCodeMapper = Collections.unmodifiableMap(codeMapper);
    }

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }


    @RequestMapping(path="", method = GET)
    public @ResponseBody List<Skill> findAllSkills() {
        List<Skill> skills = skillService.findAll();
        if (skills == null) { throw new SkillsNotFoundException(); }
        return skills;
    }


    @RequestMapping(path="/{skillId}", method = GET)
    public @ResponseBody Skill findSkillById(@PathVariable("skillId") int skillId) {
        Skill skill = skillService.findById(skillId);
        if (skill == null) { throw new SkillNotFoundException(skillId); }
        return skill;
    }

    @RequestMapping(path="/{skillId}/developers", method = GET)
    public @ResponseBody List<Developer> findAllDevelopersById(@PathVariable("skillId") int skillId) {
        List<Developer> developers = skillService.findAllDevelopers(skillId);
        if (developers == null) { throw new SkillDevelopersNotFoundException(); }
        return developers;
    }

    @RequestMapping(path="/{skillId}/developers/free", method = GET)
    public @ResponseBody List<Developer> findFreeDevelopersById(@PathVariable("skillId") int skillId) {
        List<Developer> developers = skillService.findFreeDevelopers(skillId);
        if (developers == null) { throw new SkillDevelopersNotFoundException(); }
        return developers;
    }

    @RequestMapping(path="/{skillId}/projects", method = GET)
    public List<Project> findProjectsById(@PathVariable("skillId") int skillId) {
        List<Project> projects = skillService.findProjects(skillId);
        if (projects == null) { throw new SkillProjectsNotFoundException(); }
        return projects;
    }

    @ExceptionHandler(SkillServiceException.class)
    public ResponseEntity<RestError> skillServiceExceptionHandler(SkillServiceException e) {
        RestError error = RestError.newBuilder()
                .setCode(errorCodeMapper.getOrDefault(e.getClass(),
                        RestError.GENERAL_REST_ERROR))
                .setMessage(e.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
