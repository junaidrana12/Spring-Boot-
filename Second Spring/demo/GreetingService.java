package com.example.demo;

import org.springframework.stereotype.Component;

@Component("myBean")  // this annotation basically spring manage component
public class GreetingService {
    public void sayhello(){
        System.out.println("hello from spring!......");
    }
}
