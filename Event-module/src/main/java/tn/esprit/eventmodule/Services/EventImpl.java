package tn.esprit.eventmodule.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.web.util.UriComponentsBuilder;
import tn.esprit.eventmodule.Daos.EventDao;
import tn.esprit.eventmodule.Daos.ParticipationDao;
import tn.esprit.eventmodule.Daos.ResourceReservationDao;
import tn.esprit.eventmodule.Dtos.EventAdminDto;
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

import java.util.*;

@Service
public class EventImpl implements EventInterface {

    private static final Logger LOG = LogManager.getLogger(EventImpl.class);
    private static final String ERROR_NON_PRESENT_ID = "Event with ID %s is not found.";
    private static final String ERROR_NULL_ID = "Event ID cannot be null.";



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
        LOG.info("Adding event: {}", event);
        return eventDao.save(event);
    }

    @Override
    public List<Event> getallEvent() {
        LOG.info("Retrieving all events");
        return eventDao.findAll();
    }

    @Override
    public EventAdminDto findEventById(Long eventId) {
        LOG.info("Finding event by ID: {}", eventId);
        EventAdminDto eventAdminDto = null;
        if (eventId != null) {
            Optional<Event> optionalEvent = eventDao.findById(eventId);
            if (optionalEvent.isPresent()) {
                Event event = optionalEvent.get();
                eventAdminDto = new EventAdminDto();
                // Copy event properties to eventAdminDto
                BeanUtils.copyProperties(event, eventAdminDto);
            } else {
                LOG.info(ERROR_NON_PRESENT_ID, eventId);
            }
        } else {
            LOG.error(ERROR_NULL_ID);
        }
        return eventAdminDto;
    }



    @Override
    public Event editEvent(Long eventId, Event event) {
        LOG.info("Editing event with ID {}: {}", eventId, event);
        try {
            Event existingEvent = eventDao.findById(eventId).orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + eventId));
            existingEvent.setName(event.getName());
            existingEvent.setClub(event.getClub());
            existingEvent.setEndDate(event.getEndDate());
            existingEvent.setStartDate(event.getStartDate());
            existingEvent.setRating(event.getRating());
            existingEvent.setStatus(event.getStatus());
            existingEvent.setType(event.getType());
            return eventDao.save(existingEvent);
        } catch (Exception e) {
            LOG.error("Error editing event with ID {}: {}", eventId, e.getMessage());
            throw new RuntimeException("Failed to edit event with ID: " + eventId, e);
        }
    }


    @Override
    public void deleteEvent(Long eventId) {
        LOG.info("Deleting event with ID: {}", eventId);
        eventDao.deleteById(eventId);
    }

    @Override
    @Scheduled(fixedRate = 60000) // 60000 milliseconds = 1 minute
    public void updateEventStatusAutomatiquement() {
        LOG.info("Updating event status automatically");
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
        ResourceReservation reservation = new ResourceReservation();
        reservation.setResourceID(resourceId);
        reservation.setEventId(eventId);
        resourceReservationDao.save(reservation);
    }

    public Map<String, List<ResourceDto>> displayResourcesOfEvent(Long eventId) {
        Map<String, List<ResourceDto>> resourcesByType = new HashMap<>();
        List<ResourceReservation> reservations = resourceReservationDao.findByEventId(eventId);

        for (ResourceReservation reservation : reservations) {
            ResourceDto resource = getResourceById(reservation.getResourceID());
            resource.setResourceID(null); // Remove resourceId from the resource object

            String resourceType = reservation.getResouceTypeName();
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
                .fromUriString("http://localhost:8093/api/reservations/filteredResouces")
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





