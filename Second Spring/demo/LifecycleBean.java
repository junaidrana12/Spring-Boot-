package com.example.demo;

import com.example.loose.NotificationService;

import javax.management.Notification;

public class LifecycleBean {
    private NotificationService notificationService;

    public LifecycleBean(NotificationService notificationService){
        System.out.println("Constructor Called: Dependency Injected ");
        this.notificationService = notificationService;
    }

    public void init(){
        System.out.println("init called: Bean initialized");
        notificationService.send("Hello from init()");
    }

    public void performtask(){
        System.out.println("Ready for use!");
    }

    public void cleanup(){
        System.out.println("Cleanup() being called");
    }
}
