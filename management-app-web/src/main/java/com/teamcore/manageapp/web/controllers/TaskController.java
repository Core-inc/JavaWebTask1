package com.teamcore.manageapp.web.controllers;

import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.Task;
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

        return (ResponseEntity<?>) ResponseEntity.ok();
    }



}

