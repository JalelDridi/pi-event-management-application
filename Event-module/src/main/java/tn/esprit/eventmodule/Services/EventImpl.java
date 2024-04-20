package tn.esprit.eventmodule.Services;

import tn.esprit.eventmodule.Daos.EventDao;
import tn.esprit.eventmodule.Daos.ParticipationDao;
import tn.esprit.eventmodule.Dtos.UserDto;
import tn.esprit.eventmodule.Entities.Event;
import tn.esprit.eventmodule.Entities.Participation;
import tn.esprit.eventmodule.Entities.StatusType;
import com.event.eventmodule.Daos.*;
import com.event.eventmodule.Entities.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EventImpl implements EventInterface {
    @Autowired
    private JavaMailSender emailSender;
    @Resource
    EventDao eventDao;
    @Resource
    ParticipationDao participationDao;

   /* @Autowired
    ResourceDao resourceDao;
    @Autowired
    ResourceTypeDao resourceTypeDao;

    */
    @Override
    public Event addEvent(Event event) {
        return eventDao.save(event);
    }

    @Override
    public List<Event> getallEvent() {
        return eventDao.findAll();
    }

    @Override
    public Event getAnEvent(Long eventId) {
        return eventDao.findById(eventId).get();
    }

    @Override
    public Long getEventId() {
        return null;
    }

    /*@Override
    public Long getEventId() {
        return eventId;
    }*/

    @Override
    public Event editEvent(Long eventId, Event event) {

            Event ExistingEvent = eventDao.findById(eventId).get();
            ExistingEvent.setName(event.getName());
            ExistingEvent.setClub(event.getClub());
            ExistingEvent.setEndDate(event.getEndDate());
            ExistingEvent.setStartDate(event.getStartDate());
            ExistingEvent.setRating(event.getRating());
            ExistingEvent.setStatus(event.getStatus());
            ExistingEvent.setType(event.getType());
            return eventDao.save(ExistingEvent);
    }

    @Override
    public void deleteEvent(Long eventId) {
         eventDao.deleteById(eventId);
    }

    @Override
    public List<UserDto> findByEvent(Long eventId) {
//         List<UserDto> users = new ArrayList<>();
//        return users.stream()
//                .filter(user -> user.getEventId().equals(eventId)) // Replace getEventId with the actual method to retrieve the event ID of the user
//                .collect(Collectors.toList());
        return null;
    }



    @Override
    @Scheduled(fixedRate = 60000) // 60000 milliseconds = 1 minute
    public void updateEventStatusAutomatiquement() {
        eventDao.updateEventStatus(StatusType.Planifié, StatusType.En_Cours, StatusType.Terminé);
    }


    /************************************* User
    @Override
    public User addUser(User user) {
    return userDao.save(user);
    }****************************************/

   /* @Override
    public void affectUserToEvent(Long userID, Long eventId) {
        User user= userDao.findById(userID).get();
        Event event = eventDao.findById(eventId).get();
        Participant participant = new Participant(user , event) ;
        event.getParticipants().add(participant);

        participantDao.save(participant);
        envoyerEmailParticipant(participant,event);
    }*/

    public void affectUserToEvent(String userID, long eventId) {
        // Make an HTTP request to the User Microservice to get user information
        // UserDto user = makeHttpRequestToUserMicroservice(userID);

        // Retrieve event information from the Event Microservice
        Event event = eventDao.findById(eventId).orElse(null);

        // Create a participation
        Participation participation = new Participation();
        participation.setUserID(userID);
        participation.setEventId(eventId);


        // Save participation
        participationDao.save(participation);
    }
    public void displayUserOfEvent(Long eventId) {
        // Retrieve participations for the specified event ID
        List<Participation> participations = participationDao.findByEventId(eventId);

        // Iterate over the participations
        for (Participation participation : participations) {
            // Retrieve user information for the current participation
            UserDto user = getUserById(participation.getUserID());

            // Print or process user information as needed
            System.out.println("User ID: " + user.getUserID());
            System.out.println("User Name: " + user.getFirstName());
            // Add more properties as needed

            // Do other processing with user information
        }
    }

    private UserDto getUserById(String userId) {
        // Replace "user-service-url" with the actual URL of the User Microservice
        String userMicroserviceUrl = "http://localhost:8080/api/users" + userId;

        // Make a GET request to the User Microservice
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity(userMicroserviceUrl, UserDto.class);

        // Check if the request was successful (HTTP status code 200)
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            // Handle the case where the request was not successful
            // You may throw an exception or handle it based on your application's requirements
            throw new RuntimeException("Failed to fetch user from User Microservice. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

    public void sendEmail(String toEmail, String subject, String body) {
        if (toEmail != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("medmohamed.maalej@gmail.com");
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText(body);

            emailSender.send(message);
            System.out.println("Mail Sent successfully...");
        } else {
            System.out.println("Erreur: L'adresse e-mail est non valide.");
        }
    }
    public void envoyerEmailParticipant(Participation participant, Event event ) {
        String toEmail = "";//participant.getUserID().getEmail();
        String subject = "Détails de l'événement";
        String body = "Bonjour,\n\nVous êtes inscrit à l'événement suivant :\n\n" +
                "Nom de l'événement : " + event.getName() + "\n" +
                "Date de l'événement : " + event.getStartDate() + "\n" +
                "Type de l'événement : " + event.getType() + "\n\n" +
                "Merci et à bientôt!" ;

        sendEmail(toEmail, subject, body);
    }

    /************************************* Resource

    @Override
    public Resource addResource(Resource resource) {
        return resourceDao.save(resource);
    }

    @Override
    public List<Resource> getResource() {
        return resourceDao.findAll();
    }

    @Override
    public Resource editResource(Long resourceID, Resource resource) {
        Resource ExistingResource = resourceDao.findById(resourceID).get();
        ExistingResource.setResourceName(resource.getResourceName());
        ExistingResource.setResourceType(resource.getResourceType());
        return resourceDao.save(ExistingResource);
    }

    @Override
    public void deleteResource(Long resourceID) {resourceDao.deleteById(resourceID);

    }
     ****************************************/

    /************************************* ResourceType


    @Override
    public ResourceType addResourceType(ResourceType resourceType) {
        return resourceTypeDao.save(resourceType);
    }

    @Override
    public List<ResourceType> getResourceType() {
        return resourceTypeDao.findAll();
    }

    @Override
    public ResourceType editResourceType(Long id, ResourceType resourceType) {
        ResourceType ExistingResourceType = resourceTypeDao.findById(id).get();
        ExistingResourceType.setResouceTypeName(resourceType.getResouceTypeName());
        return ExistingResourceType;
    }

    @Override
    public void deleteResourceType(Long id) { resourceTypeDao.deleteById(id);

    }
     ****************************************/

}
