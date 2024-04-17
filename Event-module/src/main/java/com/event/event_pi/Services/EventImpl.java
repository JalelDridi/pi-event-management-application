package com.event.event_pi.Services;

import com.event.event_pi.Daos.*;
import com.event.event_pi.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EventImpl implements EventInterface {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    EventDao eventDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ParticipantDao participantDao;
    @Autowired
    ResourceDao resourceDao;
    @Autowired
    ResourceTypeDao resourceTypeDao;
    @Override
    public Event addEvent(Event event) {
        return eventDao.save(event);
    }

    @Override
    public List<Event> getallEvent() {
        return eventDao.findAll();
    }

    @Override
    public Event editEvent(Long eventId, Event event) {

            Event ExistingEvent = eventDao.findById(eventId).get();
            ExistingEvent.setName(event.getName());
            ExistingEvent.setClub(event.getClub());
            ExistingEvent.setEndDate(event.getEndDate());
            ExistingEvent.setStartDate(event.getStartDate());
            ExistingEvent.setRating(event.getRating());
            ExistingEvent.setStatus(event.isStatus());
            ExistingEvent.setType(event.getType());
            return eventDao.save(ExistingEvent);
    }

    @Override
    public void deleteEvent(Long eventId) {
         eventDao.deleteById(eventId);
    }
    /************************************* User ****************************************/
    @Override
    public User addUser(User user) {
    return userDao.save(user);
    }

    @Override
    public void affectUserToEvent(Long userID, Long eventId) {
        User user= userDao.findById(userID).get();
        Event event = eventDao.findById(eventId).get();
        Participant participant = new Participant(user , event) ;
        event.getParticipants().add(participant);
        participantDao.save(participant);
        envoyerEmailParticipant(participant,event);
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
    public void envoyerEmailParticipant(Participant participant, Event event ) {
        String toEmail = participant.getUser().getEmail();
        String subject = "Détails de l'événement";
        String body = "Bonjour,\n\nVous êtes inscrit à l'événement suivant :\n\n" +
                "Nom de l'événement : " + event.getName() + "\n" +
                "Date de l'événement : " + event.getStartDate() + "\n" +
                "Type de l'événement : " + event.getType() + "\n\n" +
                "Merci et à bientôt!" ;

        sendEmail(toEmail, subject, body);
    }
    /************************************* Resource ****************************************/

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

    /************************************* ResourceType ****************************************/


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


}
