package tn.esprit.notificationmodule.controllers;


import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.entities.Notification;
import tn.esprit.notificationmodule.services.NotificationService;
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

    private static final String CONFIRM_USER_TOPIC = "confirm-user-registration";
    private static final String SEND_EMAIL_TOPIC = "send-email";
    private static final String SEND_HTML_EMAIL_TOPIC = "send-html-email";


    @PostMapping("/confirm-user")
    @ResponseBody
    public void sendNotification(@RequestBody NotificationDto notificationDto) {
        kafkaTemplate.send(CONFIRM_USER_TOPIC, notificationDto);
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
