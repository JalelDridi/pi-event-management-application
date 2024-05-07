

package tn.esprit.eventmodule.ServiceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import tn.esprit.eventmodule.Daos.EventDao;
import tn.esprit.eventmodule.Daos.ParticipationDao;
//import tn.esprit.eventmodule.Daos.ResourceReservationDao;
import tn.esprit.eventmodule.Daos.UsersEventsDao;
import tn.esprit.eventmodule.Dtos.EventAdminDto;
import tn.esprit.eventmodule.Dtos.ResourceDto;
import tn.esprit.eventmodule.Dtos.UserDto;
import tn.esprit.eventmodule.Entities.*;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.eventmodule.Services.EventInterface;

import java.util.*;

@Service
public class EventImpl implements EventInterface {

    private static final Logger LOG = LogManager.getLogger(EventImpl.class);
    private static final String ERROR_NON_PRESENT_ID = "Event with ID %s is not found.";
    private static final String ERROR_NULL_ID = "Event ID cannot be null.";

    private final WebClient.Builder webClientBuilder;

    public EventImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    JavaMailSender emailSender;
    @Resource
    EventDao eventDao;
    @Resource
    ParticipationDao participationDao;

    @Autowired
    UsersEventsDao usersEventsDao;

    @Override
    public Event addEvent(@RequestParam("file")MultipartFile file,@RequestParam("file1")MultipartFile file1, Event event, String userid) {
        LOG.info("Adding event: {}", event);

        Event e= eventDao.save(event);
        assignEventToUser(userid, event.getEventId());
        return e;
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
            existingEvent.setDescription(event.getDescription());
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
    public List<Event> getUpcomingEvents() {
        Date currentDate = new Date();
        return eventDao.findByStartDateAfterOrderByStartDate(currentDate);
    }
    public List<Event> getEventsByType(EventType type) {
        return eventDao.findByType(type);
    }
    @Override
    public void assignEventToUser(String userId, Long eventId) {
        UserDto user = this.getUserById(userId);
        if (user == null) {
            throw new IllegalStateException("No user found with ID: " + userId);
        }
        Event event = eventDao.findById(eventId)
                .orElseThrow(() -> new IllegalStateException("Event not found with ID: " + eventId));
        UserEvents userEvents = usersEventsDao.findByUserID(userId);
        if (userEvents == null) {
            userEvents = new UserEvents();
            userEvents.setUserID(userId);
            userEvents.setEvents(new ArrayList<>());
        }
        userEvents.getEvents().add(event);
        usersEventsDao.save(userEvents);
    }

    @Override
    @Scheduled(fixedRate = 60000) // 60000 milliseconds = 1 minute
    public void updateEventStatusAutomatiquement() {
        LOG.info("Updating event status automatically");
        eventDao.updateEventStatus(StatusType.Planifié, StatusType.En_Cours, StatusType.Terminé);
    }
    public void affectUserToEvent(String userID, long eventId) {
        Event event = eventDao.findById(eventId).orElse(null);
        Participation participation = new Participation();
        participation.setUserID(userID);
        participation.setEventId(eventId);
        participationDao.save(participation);
    }





    public List<UserDto> displayUserOfEvent(Long eventId) {
        List<UserDto> users = new ArrayList<>();

        List<Participation> participations = participationDao.findByEventId(eventId);
        for (Participation participation : participations) {
            List<String> userIDs = findUserIdsByEventId(participation.getEventId());
            for (String userID : userIDs) {
                UserDto user = getUserById(userID);
                users.add(user);
            }
        }

        return users;
    }

    @Override
    public UserDto getUserById(String userId) {
        String bearerToken = "eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOiI2NjMwZTNiZTMxOWFkODExZmI3ZDdjNzgiLCJzdWIiOiJhaG1lZGFtaW5lLnJvbWRuYW5pQGVzcHJpdC50biIsImlhdCI6MTcxNDg2NjI5NCwiZXhwIjoxNzE1MDM5MDk0LCJhdXRob3JpdGllcyI6WyJVU0VSIl19.7ySc_64TBrKxDNYtdfhBVbUmtrNumma_FQWma62yXsUaKBItZom8EKa_KmXC2hCp";

        String userMicroserviceUrl = UriComponentsBuilder
                .fromUriString("http://localhost:8091/api/v1/users/{userId}")
                .buildAndExpand(userId)
                .toUriString();

        Mono<UserDto> userDtoMono = webClientBuilder.build()
                .get()
                .uri(userMicroserviceUrl)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
                .retrieve()
                .bodyToMono(UserDto.class);

        return userDtoMono.block();
    }

    public List<String> findUserIdsByEventId(Long eventId) {

        return participationDao.findUserIdsByEventId(eventId);
    }

//    public void assignResourceToEvent(Long resourceId, Long eventId) {
//        ReservationDto reservation = new ReservationDto();
//        reservation.setResourceID(resourceId);
//        reservation.setEventId(eventId);
//        resourceReservationDao.save(reservation);
//    }
//
//    public Map<String, List<ResourceDto>> displayResourceOfEvent(Long eventId) {
//        Map<String, List<ResourceDto>> resourcesByType = new HashMap<>();
//        List<ResourceReservation> resourceReservations = resourceReservationDao.findByEventId(eventId);
//
//        if (resourceReservations == null) {
//            throw new IllegalStateException("Resource reservations list is null for event ID: " + eventId);
//        }



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
        }}}
