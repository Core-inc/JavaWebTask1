package com.teamcore.manageapp.web.controllers.rest;


import com.teamcore.manageapp.service.domain.Project;
import com.teamcore.manageapp.service.domain.ProjectRequest;
import com.teamcore.manageapp.service.service.ProjectRequestService;
import com.teamcore.manageapp.service.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/requests")
public class ProjectRequestController {
    private ProjectRequestService projectRequestService;

    public ProjectRequestController() {
    }

    @Autowired
    public ProjectRequestController(ProjectRequestService projectRequestService) {
        this.projectRequestService = projectRequestService;
    }

    @Autowired
    public void setProjectRequestService(ProjectRequestService projectRequestService) {
        this.projectRequestService = projectRequestService;
    }


    @GetMapping
    public ResponseEntity<?> getAllProjectRequests() {
        List<ProjectRequest> list = (List<ProjectRequest>) projectRequestService.getAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> projectRequestById(@PathVariable(value = "id") Long id) {
        ProjectRequest projectRequest = projectRequestService.getById(id);
        HttpStatus status = projectRequest != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        /*
        if (projectRequest == null) {
            Error error = new Error(4, "Project request was not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        */
        return new ResponseEntity<>(projectRequest, status);
    }

    @GetMapping(value = "/customerName/{customerName}")
    public ResponseEntity<?> projectRequestByCustomerName(@PathVariable String customerName) {
        ProjectRequest projectRequest = projectRequestService.findByCustomerName(customerName);
        HttpStatus status = projectRequest != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(projectRequest, status);
    }

    @PostMapping
    public ResponseEntity<ProjectRequest> saveProjectRequest(@RequestBody ProjectRequest projectRequest, UriComponentsBuilder ucb) {
        ProjectRequest savedProjectRequest = projectRequestService.save(projectRequest);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/requests")
                .path(String.valueOf(savedProjectRequest.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        return new ResponseEntity<>(savedProjectRequest, headers, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjectRequest(@PathVariable Long id) {
        projectRequestService.delete(id);

        return (ResponseEntity<?>) ResponseEntity.ok();
    }



}
