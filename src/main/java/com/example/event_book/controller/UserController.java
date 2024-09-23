package com.example.event_book.controller;

import com.example.event_book.model.User;
import com.example.event_book.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser=userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    //Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user=userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Update an existing users
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@RequestBody User updatedUser){
        User user=userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    //Delete a user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //Find a user by username
    @GetMapping("/username/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username){
        User user=userService.findUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
