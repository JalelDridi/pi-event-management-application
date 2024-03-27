package org.example.notificationmodule.controllers;


import org.example.notificationmodule.dtos.NotificationDto;
import org.example.notificationmodule.entities.Person;
import org.example.notificationmodule.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {


    @Autowired
    public NotificationService notificationService;



    @PostMapping("/send-notification")
    public String saveNotification(@RequestBody NotificationDto notificationDto)
    {

        notificationService.addNotification(notificationDto.getNotification(),notificationDto.getMessage());
        return "Message saved successfully !";
    }

}
