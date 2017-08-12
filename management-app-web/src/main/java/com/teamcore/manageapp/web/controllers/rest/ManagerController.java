package com.teamcore.manageapp.web.controllers.rest;

import com.teamcore.manageapp.service.domain.Manager;
import com.teamcore.manageapp.service.service.ManagerService;
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
@RequestMapping(value = "/managers",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ManagerController {
    private ManagerService managerService;

    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public ResponseEntity<?> listAllManagers() {
        List<Manager> managers = managerService.getAll();

        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getManagerById(@PathVariable Long id) {
        Manager manager = managerService.getById(id);
        if (manager == null) {
            Error error = new Error(4, "Manager was not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    @GetMapping(value = "/email/{email:.+}")
    public ResponseEntity<?> managerByEmail(@PathVariable String email) {
        Manager manager = managerService.getByEmail(email);
        HttpStatus status = manager != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(manager, status);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> managerByName(@PathVariable String name) {
        List<Manager> managers = managerService.getAllByName(name);
        HttpStatus status = managers != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(managers, status);
    }

    @PostMapping
    public ResponseEntity<Manager> saveManager(@RequestBody Manager manager,
                                               UriComponentsBuilder ucb) {
        Manager savedManager = managerService.save(manager);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/managers")
                .path(String.valueOf(savedManager.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        return new ResponseEntity<>(savedManager, headers, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Manager> updateManager(@RequestBody Manager manager,
                                                 UriComponentsBuilder ucb) {
        if (manager.getId() == null) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(manager, status);
        } else {
            Manager updatedManager = managerService.update(manager);

            HttpHeaders headers = new HttpHeaders();
            URI locationUri = ucb.path("/managers")
                    .path(String.valueOf(updatedManager.getId()))
                    .build()
                    .toUri();
            headers.setLocation(locationUri);

            return new ResponseEntity<>(updatedManager, headers, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteManager(@PathVariable Long id) {
        managerService.delete(id);

        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}
