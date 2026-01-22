package com.demo.first.app;


import org.springframework.web.bind.annotation.*;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private Map<Integer,User> userdb = new HashMap<>();

    @PostMapping
    public String createUser(@RequestBody User user){
        System.out.println(user.getEmail());
        userdb.putIfAbsent(user.getId(),user);
        return "User Created";
    }

    @PutMapping
    public String updateUser(@RequestBody User user){
        if(userdb.containsKey(user.getId()));
        userdb.put(user.getId(),user);
        return "Updated successful";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        if(!userdb.containsKey(id)){
            return "User not found";
        }
        userdb.remove(id);
        return "User deleted";
    }

    @GetMapping
    public List<User> getUser(){
        return new ArrayList<>(userdb.values());
    }
}
