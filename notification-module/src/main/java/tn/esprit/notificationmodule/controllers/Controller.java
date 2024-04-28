package tn.esprit.notificationmodule.controllers;


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
@RequestMapping("/notification")
public class Controller {

    @Autowired
    private KafkaTemplate<String, NotificationDto> kafkaTemplate;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MessageService messageService;



    // KAFKA TOPICS :
    private static final String CONFIRM_USER_TOPIC = "confirm-user-registration";

    private static final String SEND_MESSAGE_TOPIC = "send-message";
    private static final String SEND_EMAIL_TOPIC = "send-email";
    private static final String SEND_HTML_EMAIL_TOPIC = "send-html-email";


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


    @GetMapping("/get-web-notifications/{userId}")
    @ResponseBody
    public List<Message> getWebNotifs(@PathVariable String userId) {

        return notificationService.getWebNotifications(userId);
    }

    @GetMapping("/get-all-msgs")
    @ResponseBody
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }



    // SEND EMAILS USING KAFKA :
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

    // WEB NOTIFICATIONS FOR UI :

    @GetMapping("/get-message-by-id/{messageId}")
    @ResponseBody
    public Message getMessageById(@PathVariable Long messageId) {
        return messageService.getMessageById(messageId);
    }

    @GetMapping("/get-user-notif")
    @ResponseBody
    public List<Notification> getUserNotifications(@RequestParam String userId) {
        return notificationService.getNotificationsByUserId(userId);
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

}
