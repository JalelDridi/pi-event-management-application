package tn.esprit.notificationmodule.controllers;


import org.springframework.http.HttpHeaders;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;
import tn.esprit.notificationmodule.services.EmailService;
import tn.esprit.notificationmodule.services.MessageService;
import tn.esprit.notificationmodule.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/notification")
public class Controller {

    @Autowired
    private KafkaTemplate<String, NotificationDto> kafkaTemplate;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private EmailService emailService;

    // KAFKA TOPICS :
    private static final String CONFIRM_USER_TOPIC = "confirm-user-registration";
    private static final String SEND_MESSAGE_TOPIC = "send-message";
    private static final String SEND_HTML_EMAIL_TOPIC = "send-html-email";


    // SEND EMAILS USING KAFKA :

    // Send the user a confirmation for their participation in an event AND Schedule a reminder at 00:00 the day the event starts
    // This will send both an email and a web notification
    @PostMapping("/confirm-participation")
    @ResponseBody
    public void confirmParticipation(@RequestParam String userId,
                                     @RequestParam Long eventId,
                                     @RequestHeader("Authorization") String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        notificationService.confirmEventParticipation(userId, eventId, headers);
    }

    // Confirm user after registration:
    @PostMapping("/confirm-user")
    @ResponseBody
    public void confirmUserRegistration(@RequestBody NotificationDto notificationDto) {
        kafkaTemplate.send(CONFIRM_USER_TOPIC, notificationDto);
    }

    // This will send a code to reset the password:
    @PostMapping("/reset-password")
    @ResponseBody
    public void resetPassword(@RequestBody NotificationDto notificationDto) {
        kafkaTemplate.send(CONFIRM_USER_TOPIC, notificationDto);
    }

    // this will send notification in HTML format (for general purpose)
    @PostMapping("/send-notification-html")
    @ResponseBody
    public void sendNotificationHtml(NotificationDto notificationDto) {
        kafkaTemplate.send(SEND_HTML_EMAIL_TOPIC, notificationDto);
    }

    // WEB NOTIFICATIONS FOR UI :

    // This gets the content (Messages) of the web notifications of a specific user
    @GetMapping("/get-web-notifications/{userId}")
    @ResponseBody
    public List<Message> getWebNotifs(@PathVariable String userId) {

        return notificationService.getWebNotifications(userId);
    }


    // This sets the list of notifications along with their messages to true
    // TO DO
    @PatchMapping("/set-notifications-read")
    @ResponseBody
    public void setUserNotificationsAsRead(@RequestBody Long[] messageIds) {
        notificationService.setUserNotificationsAsRead(messageIds);
    }

    @GetMapping("/count-unread-notif")
    @ResponseBody
    public long countUnreadNotifications(@RequestParam String userId) {
        return notificationService.countUnreadNotifications(userId);
    }

    @GetMapping("/count-unread-messages")
    @ResponseBody
    public long countUnreadMessages() {
        return 0;
    }



    // Chat messaging :

    // Send a message to one:
    @PostMapping("/send-message")
    @ResponseBody
    public void sendMessage(@RequestBody NotificationDto notificationDto) {
        kafkaTemplate.send(SEND_MESSAGE_TOPIC, notificationDto);
    }

    // get all messages of a specific user:
    @GetMapping("/get-user-chat-messages")
    @ResponseBody
    public List<Message> getUserMessages() {
        return null;
    }

    // Set all messages as read for a specific user:
    @PostMapping("/set-messages-read/{userId}")
    @ResponseBody
    public void setUserMessagesAsRead(@PathVariable String userId) {
        messageService.setUserChatMessagesAsRead(userId);
    }


    ////////////////////////////////////////// Send upcoming events to users (THIS IS A TEST METHOD THAT IS GOING TO BE A SCHEDULED ONE LATER ON):
    @PostMapping("/send-upcoming-events")
    @ResponseBody
    public void sendUpcomingEvents() {
        emailService.sendUpcomingEvents();
    }


    // A simple Notification/Msg CRUD for testing puposes :

    @PostMapping("/add-notif")
    @ResponseBody
    public void addNotification(@RequestBody NotificationDto notificationDto) {

        Notification notification = new Notification();
        Message message = new Message();
        message.setRead(false);
        // set notification properties:
        notification.setUserId(notificationDto.getUserId());
        notification.setDeliveryChannel(notificationDto.getDeliveryChannel());
        // set message properties:
        message.setSubject(notificationDto.getSubject());
        message.setContent(notificationDto.getContent());
        message.setUserId(notificationDto.getUserId());
        message.setSentDate(new Date());
        // Add notification:
        notificationService.addNotification(notification, message);
    }

    @GetMapping("/get-all-notif")
    @ResponseBody
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }


    @GetMapping("/get-all-msgs")
    @ResponseBody
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }


}
