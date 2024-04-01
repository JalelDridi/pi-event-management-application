package org.example.notificationmodule.controllers;


import org.example.notificationmodule.dtos.NotificationDto;
import org.example.notificationmodule.entities.Notification;
import org.example.notificationmodule.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private KafkaTemplate<String, NotificationDto> kafkaTemplate;

    @Autowired
    private NotificationService notificationService;

    private static final String SEND_EMAIL_TOPIC = "send-email";
    private static final String SEND_HTML_EMAIL_TOPIC = "send-html-email";


    @PostMapping("/send-notification")
    @ResponseBody
    public void sendNotification(NotificationDto notificationDto) {
        kafkaTemplate.send(SEND_EMAIL_TOPIC, notificationDto);
    }

    @PostMapping("/send-notification-html")
    @ResponseBody
    public void sendNotificationHtml(NotificationDto notificationDto) {
        kafkaTemplate.send(SEND_HTML_EMAIL_TOPIC, notificationDto);
    }

    @GetMapping("/get-user-notif")
    @ResponseBody
    public List<Notification> getUserNotifications(@RequestParam Long userId) {
        return notificationService.getNotificationsByUserId(userId);
    }







}
