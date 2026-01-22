package com.example.demo;

import com.example.loose.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationBeanConfigration.xml");
        GreetingService greetingService =(GreetingService) context.getBean("myBean");

        greetingService.sayhello();

        UserService userService =  (UserService) context.getBean("UserServiceSMS");
        userService.notifyUser("what's up!.....");

        UserService userServiceEmail =  (UserService) context.getBean("UserServiceEmail");
        userServiceEmail.notifyUser("what's up!.....");
    }
}
