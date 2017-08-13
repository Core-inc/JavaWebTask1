package com.teamcore.manageapp.web.controllers.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Task;
import com.teamcore.manageapp.service.service.ProjectService;
import com.teamcore.manageapp.service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {
    private ProjectService projectService;
    private TaskService taskService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProjects() {
        List<Project> list = projectService.getAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> projectById(@PathVariable(value = "id") Long id) {
        Project project = projectService.getById(id);
        HttpStatus status = project != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        /*
        if (project == null) {
            Error error = new Error(4, "Project was not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        */
        return new ResponseEntity<>(project, status);
    }

    @GetMapping(value = "/internalName/{internalName}")
    public ResponseEntity<?> projectByInternalName(@PathVariable String internalName) {
        Project project = projectService.findByInternalName(internalName);
        HttpStatus status = project != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(project, status);
    }

    @GetMapping(value = "/{id}/tasks")
    public ResponseEntity<?> projectTasks(@PathVariable(value = "id") Long id) {
        List<Task> taskList = taskService.findAllTasksByProject(projectService.getById(id));

        HttpStatus status = taskList != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(taskList, status);
    }

    @PostMapping(value = "/{id}/tasks")
    public ResponseEntity<Task> addTaskForProject(@PathVariable(value = "id") Long id, @RequestBody Task task, UriComponentsBuilder ucb) {

        Task savedTask = taskService.save(task);


        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/projects/{id}/tasks")
                .path(String.valueOf(savedTask.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        return new ResponseEntity<>(savedTask, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/status/{status}")
    public ResponseEntity<?> projectByStatus(@PathVariable int status) {
        List<Project> projects = projectService.getByStatus(status);

        HttpStatus statusHttp = projects != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(projects, statusHttp);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Project> saveProject(@RequestBody Project project, UriComponentsBuilder ucb) {
        Project savedProject = projectService.save(project);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/projects")
                .path(String.valueOf(savedProject.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        return new ResponseEntity<>(savedProject, headers, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Project> updateProject(@RequestBody Project project, UriComponentsBuilder ucb) {
        if (project.getId() == null) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(project, status);
        } else {
            Project updatedProject = projectService.update(project);

            HttpHeaders headers = new HttpHeaders();
            URI locationUri = ucb.path("/projectss")
                    .path(String.valueOf(updatedProject.getId()))
                    .build()
                    .toUri();
            headers.setLocation(locationUri);

            return new ResponseEntity<>(updatedProject, headers, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        projectService.delete(id);

        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<?> getTasksOfProjects(@PathVariable Long id) {
        Project project = projectService.getById(id);
        List<Task> tasks = taskService.findAllTasksByProject(project);

        HttpStatus statusHttp = tasks != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(tasks, statusHttp);
    }
}
