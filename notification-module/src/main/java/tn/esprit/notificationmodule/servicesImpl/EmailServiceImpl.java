package tn.esprit.notificationmodule.servicesImpl;


import jakarta.annotation.Resource;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import reactor.core.publisher.Mono;
import tn.esprit.notificationmodule.dtos.NotificationDto;
import tn.esprit.notificationmodule.dtos.NotificationEventDto;
import tn.esprit.notificationmodule.dtos.NotificationUserDto;
import tn.esprit.notificationmodule.services.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    // Inject the kafka producer template
    @Autowired
    private KafkaTemplate<String, NotificationDto> kafkaTemplate;

    // Inject spring mail sender
    @Resource
    private JavaMailSender mailSender;

    // Inject template engine for processing html templates (@RequiredArgsConstructor included)
    private final TemplateEngine templateEngine;

    // Constants:
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
    public void sendUpcomingEvents() {


        // Create a WebClient instance
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8091/api/v1").build();

        // Prepare the dto for the kafka consumer:
        NotificationDto notificationDto = new NotificationDto();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request body
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "ahmedamine.romdnani@esprit.tn");
        requestBody.put("password", "11111111");

        // Create the request entity with headers and body
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        // Make a POST request to authenticate and obtain the bearer token
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(
                "http://localhost:8091/api/v1/auth/authenticate",
                requestEntity,
                Map.class);

        // Get the response body containing the token
        Map responseBody = responseEntity.getBody();

        // Extract the token from the response body
        assert responseBody != null;
        String bearerToken = (String) responseBody.get("token");
        headers.set("Authorization", "Bearer " + bearerToken);
//        headers.setBearerAuth(bearerToken);

        System.out.println(responseBody.get("token"));
        // Make a GET request to the User and Event Microservice
        try {
            // Change the return type to List<NotificationEventDto>
            List<NotificationEventDto> eventsList = restTemplate.getForObject(
                    "http://localhost:8060/Event/getall",
                    List.class); // This expects a List

            Mono<ResponseEntity<List<NotificationUserDto>>> responseMono = webClient.get()
                    .uri("/users/all")
                    .headers(httpHeaders -> httpHeaders.addAll(headers))
                    .retrieve()
                    .toEntityList(NotificationUserDto.class);

            // Block and retrieve the response
            ResponseEntity<List<NotificationUserDto>> responseEntity2 = responseMono.block();

            // Extract the list of users
            List<NotificationUserDto> usersList = responseEntity2.getBody();

            System.out.println(usersList);
            notificationDto.setContent(loadUpcomingEventsTemplate(eventsList));
            notificationDto.setEmail("ahmedamine.romdnani@esprit.tn");
            notificationDto.setSubject("Upcoming Events Newsletter");
            kafkaTemplate.send(UPCOMING_EVENTS_TOPIC, notificationDto);
        } catch (RestClientResponseException e) {
            // Handle the exception here
            System.out.println("Failed to fetch users " + e.getMessage());
        }

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

    @Override
    public String loadActivateAccountTemplate(String username, String activationCode)  {

        Context context = new Context();

        context.setVariable("username", username);
        context.setVariable("activation_code",activationCode );

        return templateEngine.process("mail_confirmation_template", context);
    }

    @Override
    public String loadUpcomingEventsTemplate(List<NotificationEventDto> notificationEventDtos) {
        // Create a Thymeleaf context
        Context context = new Context();

        // Set the list of events in the context
        context.setVariable("events", notificationEventDtos);

        // Process the template with the context
        return templateEngine.process("upcoming_events_template", context);
    }


}