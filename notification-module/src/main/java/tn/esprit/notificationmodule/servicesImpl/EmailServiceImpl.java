package tn.esprit.notificationmodule.servicesImpl;


import jakarta.annotation.Resource;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import tn.esprit.notificationmodule.dtos.NotificationEventDto;
import tn.esprit.notificationmodule.dtos.NotificationUserDto;
import tn.esprit.notificationmodule.services.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Resource
    private JavaMailSender mailSender;

    private final TemplateEngine templateEngine;


    private static final String UPCOMING_EVENTS_TOPIC = "upcoming-events";




    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    @Override
    public List<NotificationUserDto> sendUpcomingEvents() {

        // Make a GET request to the User Microservice
        RestTemplate restTemplate = new RestTemplate();
        try {
            // Change the return type to List<NotificationEventDto>
            List<NotificationEventDto> eventsList = restTemplate.getForObject(
                    "http://localhost:8060/Event/getall",
                    List.class); // This expects a List

            List<NotificationUserDto> usersList = restTemplate.getForObject(
                    "http://localhost:8060/api/v1/users/all",
                    List.class);
            return usersList;
        } catch (RestClientResponseException e) {
            // Handle the exception here
            System.out.println("Failed to fetch upcoming events: " + e.getMessage());
            return Collections.emptyList();
        }

    }

    @Override
    public void sendEmailToMany(List<String> to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }


    @Override
    public void sendHtmlEmail(String receiverMail, String Subject, String htmlTemplate) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.setFrom(new InternetAddress("ramdhaniahmedamine@gmail.com"));
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            message.setRecipients(MimeMessage.RecipientType.TO, receiverMail);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            message.setSubject(Subject);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

        try {
            message.setContent(htmlTemplate, "text/html; charset=utf-8");
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }

        mailSender.send(message);
    }

    public String loadEmailConfirmationTemplate(String username, String activationCode)  {

        Context context = new Context();

        context.setVariable("username", username);
        context.setVariable("activation_code",activationCode );

        return templateEngine.process("mail_confirmation_template", context);
    }



}