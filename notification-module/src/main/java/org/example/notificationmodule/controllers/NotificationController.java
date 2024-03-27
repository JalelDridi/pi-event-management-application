package org.example.notificationmodule.controllers;


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
    public String publishMessage(@RequestBody Person person)
    {

        // notificationService.addNotification();
        return "Message published successfully by the producer !";
    }

}
