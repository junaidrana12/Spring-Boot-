package com.demo.first.app.service;

import com.demo.first.app.Exception.UserNotFoundException;
import com.demo.first.app.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final Map<Integer, User> userDb = new HashMap<>();

    // CREATE USER
    public User createUser(User user) {
        if (userDb.containsKey(user.getId())) {
            return null; // user already exists
        }
        userDb.put(user.getId(), user);
        return user;
    }

    // UPDATE USER
    public User updateUser(User user) {
        if (!userDb.containsKey(user.getId())) {
            throw new UserNotFoundException("User with Id " + user.getId() + "does not exist");
        }
        userDb.put(user.getId(), user);
        return user;
    }

    // DELETE USER
    public boolean deleteUser(int id) {
        if(!userDb.containsKey(id))
            throw new UserNotFoundException("User with Id " + id + "does not exist");

        userDb.remove(id);
        return true;
    }

    // GET ALL USERS
    public List<User> getAllUsers() {
        throw new NullPointerException("No users found in the database");
//        return new ArrayList<>(userDb.values());
    }

    // GET USER BY ID
    public User getUserById(int id) {

        return userDb.get(id);
    }
}
