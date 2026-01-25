package com.example.service;

import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    public void addUser(String name) {
        userRepository.save(name);
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllUsers() {
       return userRepository.findAll();
    }
}
