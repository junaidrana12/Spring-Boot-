package com.example.loose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("UserServiceSMS")
public class UserService {
//    @Autowired we can use field injection like this
    public NotificationService notificationService;
    public UserService(){

    }

//    @Autowired
//    public UserService(@Qualifier("test") NotificationService notificationService){
//        this.notificationService = notificationService;
//   }

    @Autowired
    public UserService(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public void notifyUser(String message){
        notificationService.send("Notification hello");
    }
    public void setNotificationService(NotificationService notificationService){
        this.notificationService = notificationService;
    }
}
