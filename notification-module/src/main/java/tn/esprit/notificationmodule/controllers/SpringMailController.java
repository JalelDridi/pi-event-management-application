package tn.esprit.notificationmodule.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.services.EmailService;


@RestController
public class SpringMailController {

    @Autowired
    private KafkaTemplate<String, NotificationDto> kafkaTemplate;


    @Autowired
    private EmailService emailService;

    // KAFKA TOPICS :
    private static final String CONFIRMATION_CODE_USER_TOPIC = "confirm-user-registration";
    private static final String SEND_HTML_EMAIL_TOPIC = "send-html-email";
    private static final String NOTIFY_CLUB_REPRESENTATIVE_TOPIC = "notify-club-representative";
    private static final String NOTIFY_ADMIN_TOPIC = "notify-admin";


    // Confirm user after registration:
    @PostMapping("/confirm-user")
    @ResponseBody
    public void confirmUserRegistration(@RequestBody NotificationDto notificationDto) {
        kafkaTemplate.send(CONFIRMATION_CODE_USER_TOPIC, notificationDto);
    }

    // This will send a code to reset the password:
    @PostMapping("/reset-password")
    @ResponseBody
    public void resetPassword(@RequestBody NotificationDto notificationDto) {
        kafkaTemplate.send(CONFIRMATION_CODE_USER_TOPIC, notificationDto);
    }


    // Notify the admins (General purpose, eg: about event requests, resource saturation ...):
    @PostMapping("/event-request")
    @ResponseBody
    public void notifyAdmin(@RequestBody NotificationDto notificationDto) {
        kafkaTemplate.send(NOTIFY_ADMIN_TOPIC, notificationDto);
    }

    // Notify the club representative (General purpose, eg: your request has been sent, treated or rejected ...
    @PostMapping("/event-request-response")
    @ResponseBody
    public void setNotifyClubRepresentative(@RequestBody NotificationDto notificationDto) {
        kafkaTemplate.send(NOTIFY_CLUB_REPRESENTATIVE_TOPIC, notificationDto);
    }

    // this will send notification in HTML format (for general purpose)
    @PostMapping("/send-notification-html")
    @ResponseBody
    public void sendNotificationHtml(@RequestBody NotificationDto notificationDto) {
        kafkaTemplate.send(SEND_HTML_EMAIL_TOPIC, notificationDto);
    }


    ////////////////////////////////////////// Send upcoming events to users (THIS IS A TEST METHOD THAT IS GOING TO BE A SCHEDULED ONE LATER ON):
    @PostMapping("/send-upcoming-events")
    @ResponseBody
    public void sendUpcomingEvents() {
        emailService.sendUpcomingEvents();
    }





}
