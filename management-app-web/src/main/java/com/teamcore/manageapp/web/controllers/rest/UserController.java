package com.teamcore.manageapp.web.controllers.rest;

import com.teamcore.manageapp.service.domain.Role;
import com.teamcore.manageapp.service.domain.User;
import com.teamcore.manageapp.service.services.user.UserService;
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
@RequestMapping(value = "/users",
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> listAllUsers() {
        List<User> users = (List<User>) userService.listAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> userById(@PathVariable(value = "id") Integer id) {
        User user = userService.getById(id);
        if (user == null) {
            Error error = new Error(4, "User was not found.");
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/email/{email:.+}")
    public ResponseEntity<?> userByEmail(@PathVariable String email) {
        User user = userService.getByEmail(email);
        HttpStatus status = user != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(user, status);
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<?> userByName(@PathVariable String name) {
        List<User> users = userService.getAllByName(name);
        HttpStatus status = users != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(users, status);
    }

    @GetMapping("/{id}/role")
    public ResponseEntity<?> userRole(@PathVariable Integer id) {
        Role role = userService.getRoleByUserId(id);
        HttpStatus status = role != null ?
                HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(role, status);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user, UriComponentsBuilder ucb) {
        User savedUser = userService.saveOrUpdate(user);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/users")
                .path(String.valueOf(savedUser.getId()))
                .build()
                .toUri();
        headers.setLocation(locationUri);

        return new ResponseEntity<>(savedUser, headers, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestBody User user, UriComponentsBuilder ucb) {
        if (user.getId() == null) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(user, status);
        } else {
            User updatedUser = userService.saveOrUpdate(user);

            HttpHeaders headers = new HttpHeaders();
            URI locationUri = ucb.path("/users")
                    .path(String.valueOf(updatedUser.getId()))
                    .build()
                    .toUri();
            headers.setLocation(locationUri);

            return new ResponseEntity<>(updatedUser, headers, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        boolean isDeleted = userService.delete(id);

        if (!isDeleted) {
            //TODO exception
        }

        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}
