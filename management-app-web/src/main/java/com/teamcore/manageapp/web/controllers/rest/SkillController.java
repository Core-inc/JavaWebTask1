package com.teamcore.manageapp.web.controllers.rest;

import com.teamcore.manageapp.service.domain.Skill;
import com.teamcore.manageapp.service.service.SkillService;
import com.teamcore.manageapp.service.service.exceptions.skill.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/skills", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SkillController {

    private SkillService skillService;

    private final static Map<Class<? extends SkillServiceException>, RestError.Code> errorCodeMapper;

    static {
        Map<Class<? extends SkillServiceException>, RestError.Code> codeMapper = new HashMap<>();
        codeMapper.put(SkillServiceException.class, RestError.Code.GENERAL_REST_ERROR);

        codeMapper.put(SkillNotFoundException.class, RestError.Code.SKILL_NOT_FOUND_CODE);
        codeMapper.put(SkillsNotFoundException.class, RestError.Code.SKILLS_NOT_FOUND_CODE);

        codeMapper.put(SkillAddException.class, RestError.Code.SKILL_ADD_CODE);
        codeMapper.put(SkillUpdateException.class, RestError.Code.SKILL_UPDATE_CODE);
        codeMapper.put(SkillDeleteException.class, RestError.Code.SKILL_DELETE_CODE);

        codeMapper.put(SkillDevelopersNotFoundException.class, RestError.Code.SKILL_DEVELOPERS_NOT_FOUND_CODE);
        codeMapper.put(SkillProjectsNotFoundException.class, RestError.Code.SKILL_PROJECTS_NOT_FOUND_CODE);

        errorCodeMapper = Collections.unmodifiableMap(codeMapper);
    }

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @RequestMapping(path="", method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Skill addSkill(@Valid @RequestBody Skill newSkill) {
        try {
            return skillService.save(newSkill);
        } catch(SkillServiceException e) {
            throw e;
        } catch(Exception e) {
            throw new SkillAddException();
        }
    }

    @RequestMapping(path="/{skillId}", method = PUT)
    public @ResponseBody Skill updateSkill(@Valid @RequestBody Skill newSkill) {
        try {
            return skillService.update(newSkill);
        } catch(SkillServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new SkillUpdateException();
        }
    }

    @RequestMapping(path="/{skillId}", method = DELETE)
    public ResponseEntity<?> deleteSkill(@PathVariable("skillId") long skillId) {
        try {
            skillService.delete(skillId);
            return ResponseEntity.ok("delete success");
        } catch(SkillServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new SkillDeleteException();
        }
    }

    @RequestMapping(path="", method = GET)
    public @ResponseBody List<Skill> findAllSkills() {
        try {
            return skillService.getAll();
        } catch(SkillServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new SkillsNotFoundException();
        }
    }


    @RequestMapping(path="/{skillId}", method = GET)
    public @ResponseBody Skill findSkillById(@PathVariable("skillId") long skillId) {
        try {
            return skillService.getById(skillId);
        } catch (SkillServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new SkillNotFoundException(skillId);
        }
    }

    @RequestMapping(path = "/developer/{developerId}", method = GET)
    public @ResponseBody Skill findSkillByDeveloperId(@PathVariable("developerId") long developerId) {
        try {
            return skillService.getSkillByDeveloperId(developerId);
        } catch (SkillServiceException e) {
            throw e;
        }
    }

//    @RequestMapping(path="/{skillId}/developers", method = GET)
//    public @ResponseBody List<Developer> findAllDevelopersById(@PathVariable("skillId") int skillId) {
//        List<Developer> developers = skillService.findAllDevelopers(skillId);
//        if (developers == null) { throw new SkillDevelopersNotFoundException(); }
//        return developers;
//    }

//    @RequestMapping(path="/{skillId}/developers/free", method = GET)
//    public @ResponseBody List<Developer> findFreeDevelopersById(@PathVariable("skillId") int skillId) {
//        List<Developer> developers = skillService.findFreeDevelopers(skillId);
//        if (developers == null) { throw new SkillDevelopersNotFoundException(); }
//        return developers;
//    }
//
//    @RequestMapping(path="/{skillId}/projects", method = GET)
//    public List<Project> findProjectsById(@PathVariable("skillId") int skillId) {
//        List<Project> projects = skillService.findProjects(skillId);
//        if (projects == null) { throw new SkillProjectsNotFoundException(); }
//        return projects;
//    }

    @ExceptionHandler(SkillServiceException.class)
    public ResponseEntity<RestError> skillServiceExceptionHandler(SkillServiceException e) {
        RestError.Code errorCode = errorCodeMapper.getOrDefault(e.getClass(),
                RestError.Code.GENERAL_REST_ERROR);

        RestError error = RestError.newBuilder()
                .setCode(errorCode.getServiceCode())
                .setMessage(e.getMessage())
                .build();
        return new ResponseEntity<>(error, errorCode.getHttpStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<RestError> skillServiceValidationHandler(ValidationException e) {
        RestError error = RestError.newBuilder()
                .setCode(0)
                .setMessage("invalid data")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestError> generalSkillServiceExceptionHandler(Exception e) {
        RestError.Code errorCode = RestError.Code.GENERAL_REST_ERROR;
        RestError error = RestError.newBuilder()
                .setCode(errorCode.getServiceCode())
                .setMessage("invalid service request")
                .build();
        return new ResponseEntity<>(error, errorCode.getHttpStatus());
    }
}
