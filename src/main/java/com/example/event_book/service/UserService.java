package com.example.event_book.service;

import com.example.event_book.exception.ResourceNotFoundException;
import com.example.event_book.model.User;
import com.example.event_book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder; // Use dependency injection


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Create new user
    public User createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException("Username already exists");
        if (userRepository.existsByEmail(user.getEmail()))
            throw new IllegalArgumentException("Email already exists");
        return userRepository.save(user);
    }

    //Get user by id
    public User getUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found by id"+id));
    }

    //Get all users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //Update User
    public User updateUser(Long id,User updatedUser){
        User existingUser=getUserById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setName(updatedUser.getName());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setPhoto(updatedUser.getPhoto());
        existingUser.setDob(updatedUser.getDob());
        existingUser.setGender(updatedUser.getGender());

        return userRepository.save(existingUser);
    }

    //Delete User
    public void deleteUser(Long id){
        User user=getUserById(id);
        userRepository.delete(user);
    }

    //Find user by username
    public User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with username:"+username));
    }

    public boolean checkPassword(String rawPassword, String storedPassword) {
        return passwordEncoder.matches(rawPassword, storedPassword);
    }

    private String hashPassword(String password) {
        // You can add password hashing logic here (e.g., BCrypt)
        return password; // Simplified, in real use BCrypt for hashing
    }
}
