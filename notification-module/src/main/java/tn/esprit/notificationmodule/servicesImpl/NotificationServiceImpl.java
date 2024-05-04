package tn.esprit.notificationmodule.servicesImpl;

import jakarta.annotation.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.dtos.NotificationEventDto;
import tn.esprit.notificationmodule.dtos.NotificationUserDto;
import tn.esprit.notificationmodule.entities.Message;
import tn.esprit.notificationmodule.entities.Notification;
import tn.esprit.notificationmodule.enums.DeliveryChannel;
import tn.esprit.notificationmodule.enums.MessageType;
import tn.esprit.notificationmodule.repositories.MessageRepository;
import tn.esprit.notificationmodule.repositories.NotificationRepository;
import tn.esprit.notificationmodule.services.NotificationService;
import tn.esprit.notificationmodule.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class NotificationServiceImpl implements NotificationService {

    // A sequence generator that generates entity ids with increment option
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    // Kafka template that takes a NotificationDto
    @Autowired
    private KafkaTemplate<String, NotificationDto> kafkaTemplate;

    // Inject mongo repositories:
    @Resource
    private NotificationRepository notificationRepository;
    @Resource
    private MessageRepository messageRepository;

    // Kafka topics to send participation mail:
    private static final String PARTICIPATION_TOPIC = "participation";




    @Override
    public List<Message> getWebNotifications(String userId) {
        List<Notification> notifications = notificationRepository.findNotificationByUserIdAndDeliveryChannel(userId, DeliveryChannel.webNotification);
        List<Message> messages = new ArrayList<>();
        for (Notification notification : notifications) {
            messages.add(messageRepository.findByMessageId(notification.getMessageId()));
        }
        return messages;
    }

    @Override
    public List<Notification> getNotificationsByUserId(String userId) {
        return notificationRepository.findNotificationByUserId(userId);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> getReadNotificationsByUserId(String userId) {
        return notificationRepository.findNotificationByUserIdAndIsRead(userId, true);
    }

    @Override
    public long countUnreadNotifications(String userId) {
        return notificationRepository.countByUserIdAndIsReadAndDeliveryChannel(userId, false, DeliveryChannel.webNotification);
    }

    @Override
    public void addNotification(Notification notification, Message message) {
        message.setMessageId(sequenceGeneratorService.generateSequence(Message.SEQUENCE_NAME));
        message.setRead(false);
        messageRepository.save(message);

        notification.setMessageId(message.getMessageId());
        notification.setNotificationId(sequenceGeneratorService.generateSequence(Notification.SEQUENCE_NAME));
        notification.setIsRead(false);
        notificationRepository.save(notification);
    }




    @Override
    public void confirmEventParticipation(String userId, Long eventId, HttpHeaders headers) {

        // Make a GET request to the User Microservice
        RestTemplate restTemplate = new RestTemplate();



        // Change the return type to List<NotificationEventDto>
        NotificationEventDto event = restTemplate.getForObject(
                "http://localhost:8060/Event/getbyid",
                NotificationEventDto.class); // This expects a List

        // Create HttpEntity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);


        NotificationUserDto user = restTemplate.getForObject(
                "http://localhost:8060/api/v1/users/"+userId,
                NotificationUserDto.class,
                entity);

        NotificationDto notificationDto = new NotificationDto();
        assert user != null;
        assert event != null;
        notificationDto.setEmail(user.getEmail());
        notificationDto.setFullName(user.getFullName());


        Message message = new Message();
        message.setSubject("Confirmation of you participation");
        message.setContent("Hello" + user.getFullName() + ", thank you for your participation in the event : " + event.getName());
        message.setUserId(user.getUserId());
        message.setSentDate(new Date());
        message.setMessageType(MessageType.webNotification);
        // the notification is sent will be set to true since it will be sent instantly


        // Save this to the database ,so it can be retrieved later by the web client (web notification)
        Notification notification = new Notification();
        notification.setDeliveryChannel(DeliveryChannel.webNotification);
        notification.setIsSent(true);
        // Saves the web notification along with its message (content).
        addNotification(notification, message);

        // prepare to send an email:
        notification.setDeliveryChannel(DeliveryChannel.email);
        message.setMessageType(MessageType.email);
        // Saves the mail notification along with its message (content).
        addNotification(notification, message);
        notificationDto.setNotificationAndMessage(notification, message);
        kafkaTemplate.send(PARTICIPATION_TOPIC, notificationDto);

        // the is sent will be set to false, so it can be sent later on .
        Notification reminderNotification = new Notification();
        message.setSentDate(event.getStartDate());
        reminderNotification.setIsSent(false); // Because it's going to be sent later.
        message.setSubject("Reminder for your participation");
        message.setContent("Hello" + user.getFullName() + ", Today is the day of the event : " + event.getName() + ". don't miss out !");
        addNotification(reminderNotification, message); // this will be scheduled for later
    }

    @Override
    public void setUserNotificationsAsRead(Long[] messageIds) {
        for (Long id : messageIds) {
            Message message = messageRepository.findByMessageId(id);
            if (message != null) {
                message.setRead(true);
                messageRepository.save(message);

                List<Notification> notifications = notificationRepository.findNotificationByMessageId(id);
                for (Notification notification : notifications) {
                    notification.setIsRead(true);
                }
                notificationRepository.saveAll(notifications);
            } else {
                // TO DO : Replace it later with a Logger
                System.out.println("Message with ID " + id + " not found.");
            }
        }
    }

    @Override
    public void deleteNotification(Long id) {

        Message message = messageRepository.findByMessageId(id);
        List<Notification> notifications = notificationRepository.findNotificationByMessageId(id);
        if (message != null) {
            messageRepository.delete(message);
            notificationRepository.deleteAll(notifications);
        }

    }

    @Override
    public void sendWebNotifications(NotificationDto notificationDto) {
        Notification notification = new Notification();
        Message message = new Message();

        if (notificationDto != null) {
            message.setSubject(notificationDto.getSubject());
            message.setContent(notificationDto.getContent());
            message.setUserId(notificationDto.getUserId());
            message.setSentDate(new Date());
            message.setMessageType(MessageType.webNotification);

            notification.setIsRead(false);
            notification.setUserId(notificationDto.getUserId());
            notification.setDeliveryChannel(DeliveryChannel.webNotification);
            notification.setIsSent(true);

            addNotification(notification, message);

        }
    }


    // Scheduled method to remind users of their participations.
    @Scheduled(cron = "0 0 8 * * ?") // Will be executed every day at 8 AM
    public void remindUsersOfParticipations() {
        List<Notification> notifications = notificationRepository.findNotificationByIsSent(false);
        Message message; // initialise message entity
        Date now = new Date(); // Right now


        for (Notification notification : notifications) {
            NotificationDto notificationDto = new NotificationDto();
            message = messageRepository.findByMessageId(notification.getMessageId());
            // difference in milliseconds between the scheduled send date and now
            long timeDifferenceMillis = message.getSentDate().getTime() - now.getTime();
            long timeDifferenceHours = timeDifferenceMillis / (1000 * 60 * 60);
            if (message.getSentDate().after(now) && timeDifferenceHours < 24) {
                // The condition is satisfied
                notificationDto.setNotificationAndMessage(notification, message);
                notificationDto.setEmail("ahmedamine.romdnani@esprit.tn");
                System.out.println("Send date is within the next 24 hours.");
                notification.setIsSent(true);
                notificationRepository.save(notification);

                // if the delivery channel is
                if(notification.getDeliveryChannel() == DeliveryChannel.email) {
                    kafkaTemplate.send(PARTICIPATION_TOPIC, notificationDto);
                }

            } else {
                // The condition is not satisfied
                System.out.println("Send date is either not in the future or more than 24 hours away.");
            }
        }
    }


}