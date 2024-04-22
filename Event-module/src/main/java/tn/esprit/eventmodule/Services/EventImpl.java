package tn.esprit.eventmodule.Services;

import org.springframework.web.util.UriComponentsBuilder;
import tn.esprit.eventmodule.Daos.EventDao;
import tn.esprit.eventmodule.Daos.ParticipationDao;
import tn.esprit.eventmodule.Daos.ResourceReservationDao;
import tn.esprit.eventmodule.Dtos.ResourceDto;
import tn.esprit.eventmodule.Dtos.UserDto;
import tn.esprit.eventmodule.Entities.*;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventImpl implements EventInterface {

    JavaMailSender emailSender;
    @Resource
    EventDao eventDao;
    @Resource
    ParticipationDao participationDao;
    @Resource
    ResourceReservationDao resourceReservationDao;

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
    @Scheduled(fixedRate = 60000) // 60000 milliseconds = 1 minute
    public void updateEventStatusAutomatiquement() {
        eventDao.updateEventStatus(StatusType.Planifié, StatusType.En_Cours, StatusType.Terminé);
    }
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

    public List<UserDto> displayUserOfEvent(Long eventId) {
        List<UserDto> users = new ArrayList<>();

        // Retrieve participations for the specified event ID
        List<Participation> participations = participationDao.findByEventId(eventId);

        // Iterate over the participations
        for (Participation participation : participations) {
            // Retrieve user IDs associated with the current participation
            List<String> userIDs = findUserIdsByEventId(participation.getEventId());

            // Iterate over the user IDs
            for (String userID : userIDs) {
                // Retrieve user information for the current user ID
                UserDto user = getUserById(userID);
                users.add(user);
            }
        }

        return users;
    }


    private UserDto getUserById(String userId) {
        // Replace "user-service-url" with the actual URL of the User Microservice
// Construct the URL with placeholders
        String userMicroserviceUrl = UriComponentsBuilder
                .fromUriString("http://localhost:8091/api/v1/users/{userId}")
                .buildAndExpand(userId)
                .toUriString();
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

    public List<String> findUserIdsByEventId(Long eventId) {

        return participationDao.findUserIdsByEventId(eventId);
    }
    /**************************************
     *                                    Resource
     *                                          ***********************************************/
    public void assignResourceToEvent(Long resourceId, Long eventId) {
        // Create a resource reservation
        ResourceReservation reservation = new ResourceReservation();
        reservation.setResourceID(resourceId);
        reservation.setEventId(eventId);

        // Save resource reservation
        resourceReservationDao.save(reservation);
    }

    public Map<String, List<ResourceDto>> displayResourcesOfEvent(Long eventId) {
        Map<String, List<ResourceDto>> resourcesByType = new HashMap<>();

        // Retrieve resource reservations for the specified event ID
        List<ResourceReservation> reservations = resourceReservationDao.findByEventId(eventId);

        // Iterate over the resource reservations
        for (ResourceReservation reservation : reservations) {
            // Retrieve resource information for the current reservation
            ResourceDto resource = getResourceById(reservation.getResourceID());

            // Retrieve resource type information for the current resource
            String resourceType = reservation.getResouceTypeName();

            // Remove resourceId from the resource object
            resource.setResourceID(null);

            // Add the resource to the corresponding list based on resource type
            List<ResourceDto> resources = resourcesByType.getOrDefault(resourceType, new ArrayList<>());
            resources.add(resource);
            resourcesByType.put(resourceType, resources);
        }

        return resourcesByType;
    }

    private ResourceDto getResourceById(Long resourceId) {
        // Replace "resource-service-url" with the actual URL of the Resource Microservice
        // Construct the URL with placeholders
        String resourceMicroserviceUrl = UriComponentsBuilder
                .fromUriString("http://localhost:8093/api/resources/{id}")
                .buildAndExpand(resourceId)
                .toUriString();

        // Make a GET request to the Resource Microservice
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResourceDto> responseEntity = restTemplate.getForEntity(resourceMicroserviceUrl, ResourceDto.class);

        // Check if the request was successful (HTTP status code 200)
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            // Handle the case where the request was not successful
            throw new RuntimeException("Failed to fetch resource from Resource Microservice. Status code: " + responseEntity.getStatusCodeValue());
        }
    }

//    public void sendEmail(String toEmail, String subject, String body) {
//        if (toEmail != null) {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("medmohamed.maalej@gmail.com");
//            message.setTo(toEmail);
//            message.setSubject(subject);
//            message.setText(body);
//
//            emailSender.send(message);
//            System.out.println("Mail Sent successfully...");
//        } else {
//            System.out.println("Erreur: L'adresse e-mail est non valide.");
//        }
//    }
//    public void envoyerEmailParticipant(Participation participant, Event event ) {
//        String toEmail = "";//participant.getUserID().getEmail();
//        String subject = "Détails de l'événement";
//        String body = "Bonjour,\n\nVous êtes inscrit à l'événement suivant :\n\n" +
//                "Nom de l'événement : " + event.getName() + "\n" +
//                "Date de l'événement : " + event.getStartDate() + "\n" +
//                "Type de l'événement : " + event.getType() + "\n\n" +
//                "Merci et à bientôt!" ;
//
//        sendEmail(toEmail, subject, body);
//    }
//    public void displayReclamationsForEvent(long eventId) {
//        Event event = eventDao.findById(eventId).orElse(null);
//        if (event != null) {
//            // Assuming you have a method to get all reclamations for an event from the event object
//            List<ReclamationDto> allReclamations = event.getReclamations();
//
//            // Display reclamations with user ID
//            for (ReclamationDto reclamation : allReclamations) {
//                System.out.println("Reclamation: " + reclamation.getId() + ", User ID: " + reclamation.getUserId());
//            }
//        } else {
//            // Handle case where event is not found
//            System.out.println("Event not found!");
//        }
//    }
    /****************************
     *                              Statistiques
     *                                          **********************************/
    @Override
    public Map<String, Map<String, Double>> calculateEventPercentageByTypeAndStatus() {
        Map<String, Map<String, Double>> percentages = new HashMap<>();

        // Récupérer tous les types d'événements
        for (EventType eventType : EventType.values()) {
            // Initialiser le sous-map pour ce type d'événement
            Map<String, Double> typePercentage = new HashMap<>();

            // Récupérer le nombre total d'événements pour ce type
            List<Event> eventsByType = eventDao.findByType(eventType);
            int totalEventsOfType = eventsByType.size();

            // Calculer le pourcentage pour chaque statut
            for (StatusType statusType : StatusType.values()) {
                // Récupérer le nombre d'événements pour ce statut
                List<Event> eventsByTypeAndStatus = eventDao.findByTypeAndStatus(eventType, statusType);
                int totalEventsOfTypeAndStatus = eventsByTypeAndStatus.size();

                // Calculer le pourcentage
                double percentage = (totalEventsOfTypeAndStatus / (double) totalEventsOfType) * 100;

                // Ajouter le pourcentage au sous-map
                typePercentage.put(statusType.toString(), percentage);
            }

            // Ajouter le sous-map au map principal
            percentages.put(eventType.toString(), typePercentage);
        }

        return percentages;
    }
    public void displayEventPercentages() {
        Map<String, Map<String, Double>> percentages = calculateEventPercentageByTypeAndStatus();

        // Affichage des pourcentages
        for (Map.Entry<String, Map<String, Double>> entry : percentages.entrySet()) {
            String eventType = entry.getKey();
            System.out.println(eventType + ":");

            Map<String, Double> typePercentages = entry.getValue();
            System.out.println("  Planifié: " + typePercentages.getOrDefault("Planifié", 0.0) + "%");
            System.out.println("  En cours: " + typePercentages.getOrDefault("En_Cours", 0.0) + "%");
            System.out.println("  Terminé: " + typePercentages.getOrDefault("Terminé", 0.0) + "%");
        }
    }
}





