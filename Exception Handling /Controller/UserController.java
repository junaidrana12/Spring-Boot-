package com.demo.first.app.controller;

import com.demo.first.app.model.User;
import com.demo.first.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // Constructor Injection
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        if (createdUser == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }

    // GET ALL USERS
    @GetMapping
      public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }


    // /user/search?name=John&email=john@gmail.com
    @GetMapping("/search")
      public ResponseEntity<List<User>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        List<User> users = userService.getAllUsers();

        if (name != null) {
            users = users.stream()
                    .filter(u -> u.getName().equalsIgnoreCase(name))
                    .toList();
        }

        if (email != null) {
            users = users.stream()
                    .filter(u -> u.getEmail().equalsIgnoreCase(email))
                    .toList();
        }

        return ResponseEntity.ok(users);
    }

    // USING PATH + PARAM + HEADER TOGETHER
    @GetMapping("/info/{id}")
    public String getInfo(@PathVariable int id,
                          @RequestParam String name,
                          @RequestHeader("User-Agent") String userAgent) {

        return "User-Agent: " + userAgent + " | Id: " + id + " | Name: " + name;
    }

}