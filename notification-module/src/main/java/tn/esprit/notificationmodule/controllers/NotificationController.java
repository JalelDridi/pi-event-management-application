package tn.esprit.notificationmodule.controllers;


import org.springframework.http.HttpHeaders;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;
import tn.esprit.notificationmodule.services.MessageService;
import tn.esprit.notificationmodule.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    private KafkaTemplate<String, NotificationDto> kafkaTemplate;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MessageService messageService;


    // KAFKA TOPICS :
    private static final String SEND_MESSAGE_TOPIC = "send-message";


    // SEND EMAILS USING KAFKA :

    // Send the user a confirmation for their participation in an event AND Schedule a reminder at 00:00 the day the event starts
    // This will send both an email and a web notification
    @PostMapping("/confirm-participation")
    @ResponseBody
    public void confirmParticipation(@RequestParam String userId,
                                     @RequestParam Long eventId,
                                     @RequestHeader("Authorization") String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(authorizationHeader);
        headers.set("Authorization", authorizationHeader);
        notificationService.confirmEventParticipation(userId, eventId, headers);
    }



    // WEB NOTIFICATIONS FOR UI :

    // This gets the content (Messages) of the web notifications of a specific user
    @GetMapping("/get-web-notifications/{userId}")
    @ResponseBody
    public List<Message> getWebNotifs(@PathVariable String userId) {

        return notificationService.getWebNotifications(userId);
    }


    // Send web notification

    @PostMapping("/send-web-notification")
    @ResponseBody
    public void sendWebNotification(@RequestBody NotificationDto notificationDto) {
        notificationService.sendWebNotifications(notificationDto);
    }

    // Delete a notification along with its content (message)

    @DeleteMapping("/delete-notification/{id}")
    @ResponseBody
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }

    // This sets the list of notifications along with their messages to true
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
    public List<Message> getChatMessages() {
        return messageService.getChatMessages();
    }

    // Set all messages as read for a specific user:
    @PostMapping("/set-messages-read/{userId}")
    @ResponseBody
    public void setChatMessagesAsRead(@PathVariable String userId) {
        messageService.setUserChatMessagesAsRead(userId);
    }


    @GetMapping("/count-unread-messages")
    @ResponseBody
    public long countUnreadMessages() {
        return messageService.countUnreadMessages();
    }

    // A simple Notification/Msg CRUD for testing purposes :

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
