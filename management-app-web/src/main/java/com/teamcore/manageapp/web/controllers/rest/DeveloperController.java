package com.teamcore.manageapp.web.controllers.rest;

import com.teamcore.manageapp.service.domain.Developer;
import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;
import com.teamcore.manageapp.service.service.DeveloperService;
import com.teamcore.manageapp.service.service.UserService;
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
@RequestMapping(value = "/developers",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeveloperController {
    private DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @Autowired
    public void setDeveloperService(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public ResponseEntity<?> listAllDevelopers() {
        List<Developer> developers = (List<Developer>) developerService.getAll();

        return new ResponseEntity<>(developers, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> developerById(@PathVariable(value = "id") Long id) {
        Developer developer = developerService.getById(id);
        if (developer == null) {
            Error error = new Error(4, "User was not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(developer, HttpStatus.OK);
    }

    @GetMapping(value = "/email/{email:.+}")
    public ResponseEntity<?> developerByEmail(@PathVariable String email) {
        Developer developer = developerService.getByEmail(email);
        HttpStatus status = developer != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(developer, status);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> developerByName(@PathVariable String name) {
        List<Developer> developers = developerService.getAllByName(name);
        HttpStatus status = developers != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(developers, status);
    }

    @GetMapping(value = "/{id}/status")
    public ResponseEntity<?> developerStatus(@PathVariable Long id) {
        return new ResponseEntity<>(developerService.getDeveloperStatus(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Developer> saveDeveloper(@RequestBody Developer developer, UriComponentsBuilder ucb) {
        Developer savedDeveloper = developerService.save(developer);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/developers")
                .path(String.valueOf(savedDeveloper.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        return new ResponseEntity<>(savedDeveloper, headers, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<User> updateDeveloper(@RequestBody Developer developer, UriComponentsBuilder ucb) {
        if (developer.getId() == null) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(developer, status);
        } else {
            User updatedUser = developerService.update(developer);

            HttpHeaders headers = new HttpHeaders();
            URI locationUri = ucb.path("/developers")
                    .path(String.valueOf(updatedUser.getId()))
                    .build()
                    .toUri();
            headers.setLocation(locationUri);

            return new ResponseEntity<>(updatedUser, headers, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeveloper(@PathVariable Long id) {
        developerService.delete(id);

        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}
