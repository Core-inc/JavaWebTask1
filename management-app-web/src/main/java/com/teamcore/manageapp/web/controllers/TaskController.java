package com.teamcore.manageapp.web.controllers;

import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Task;
import com.teamcore.manageapp.service.service.DeveloperService;
import com.teamcore.manageapp.service.service.ProjectService;
import com.teamcore.manageapp.service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    private TaskService taskService;
    private DeveloperService developerService;

    public TaskController() {
    }

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setDeveloperService(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTasks() {
        List<Task> list = (List<Task>) taskService.getAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> projectById(@PathVariable(value = "id") Long id) {
        Task task = taskService.getById(id);
        HttpStatus status = task != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        /*
        if (task == null) {
            Error error = new Error(4, "Task was not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        */
        return new ResponseEntity<>(task, status);
    }

    @GetMapping(value = "/{id}/developers")
    public ResponseEntity<?> developersByTask(@PathVariable(value = "id") Long id) {
        List<Developer> devList = taskService.getDeveloperByTask(taskService.getById(id));
        HttpStatus status = devList != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        /*
        if (task == null) {
            Error error = new Error(4, "Task was not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        */
        return new ResponseEntity<>(devList, status);
    }

    @PostMapping
    public ResponseEntity<Task> saveTaskWithDeveloper(@RequestBody Task task/*, @RequestBody Developer developer*/, UriComponentsBuilder ucb) {
        Task savedTask = taskService.save(task);
//        addDeveloperToTask(developer.getId(), savedTask.getId());

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/tasks")
                .path(String.valueOf(savedTask.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        return new ResponseEntity<>(savedTask, headers, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task, UriComponentsBuilder ucb) {
        if (task.getId() == null) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(task, status);
        } else {
            Task updatedTask = taskService.update(task);

            HttpHeaders headers = new HttpHeaders();
            URI locationUri = ucb.path("/tasks")
                    .path(String.valueOf(updatedTask.getId()))
                    .build()
                    .toUri();
            headers.setLocation(locationUri);

            return new ResponseEntity<>(updatedTask, headers, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        taskService.delete(id);

        return ResponseEntity.ok("");
    }

    @PostMapping("/{taskId}/developer/{developerId}")
    public ResponseEntity<?> addDeveloperToTask(@PathVariable Long taskId, @PathVariable Long developerId) {
        taskService.addDeveloperToTask(developerService.getById(developerId), taskService.getById(taskId));

        return ResponseEntity.ok("");
    }
}

