package com.demo.first.app;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<User> createUser(@RequestBody User user){
        System.out.println(user.getEmail());
        userdb.putIfAbsent(user.getId(),user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
        return new ResponseEntity<>(user , HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        if(!userdb.containsKey(user.getId()))
        return ResponseEntity.notFound().build();
            userdb.put(user.getId(), user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        if(!userdb.containsKey(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userdb.remove(id);
//        return ResponseEntity.ok("User Deleted");
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<User> getUser(){
        return new ArrayList<>(userdb.values());
    }

    // /user/10 , /user/300
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") int id) {
        if (!userdb.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userdb.get(id));
    }

        @GetMapping("/{userId}/orders/{orderId}")
        public ResponseEntity<User> getUserOrder(
                @PathVariable("userId") int id,
                @PathVariable int orderId) {
            System.out.println("ORDER ID: " + orderId);
            if (!userdb.containsKey(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(userdb.get(id));
        }

        //  /search?name=john
        @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam(required = false, defaultValue = "alice") String name,
                                                  @RequestParam(required = false,defaultValue = "department") String email){
            System.out.println("Name: "+name +" "+", Email: "+ email);
            List<User> users = userdb.values().stream()
                    .filter(u ->u.getName().equalsIgnoreCase(name))
                    .filter(u ->u.getEmail().equalsIgnoreCase(email)).toList();
            return ResponseEntity.ok(users);
        }

     // can we use all the annotation together ?
            // yes
        @GetMapping("/info/{id}")
    public String getInfo(@PathVariable int id,
                          @RequestParam String name,
                          @RequestHeader("User-Agent") String userAgent){
         return "User-Agent " +userAgent + " : " + id + " : "+ name;
        }
}

