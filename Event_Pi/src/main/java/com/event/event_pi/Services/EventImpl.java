package com.event.event_pi.Services;

import com.event.event_pi.Daos.EventDao;
import com.event.event_pi.Daos.ParticipantDao;
import com.event.event_pi.Daos.UserDao;
import com.event.event_pi.Entities.Event;
import com.event.event_pi.Entities.Participant;
import com.event.event_pi.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
    /*** User */
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


}
