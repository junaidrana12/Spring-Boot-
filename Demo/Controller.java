package com.demo.first;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!....";
    }

   // @GetMapping("/user")
   // we can  write like this with the help
   // of Requestmapping mostly casese we use it on the class level
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public User getUser() {
//        User user = new User(1,"John", "john@examle.com");
//        return user;
//    }
}
