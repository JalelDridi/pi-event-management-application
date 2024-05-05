package tn.esprit.review_module.services;

import tn.esprit.review_module.entities.Reclamation;

import java.util.List;

public interface ReclamationService {

    public Reclamation addReclamation(Reclamation reclamation);

    public List<Reclamation> getAllReclamations();

    //public void SendReclamationEmail(String email);
    public List<Reclamation> findByeventId(Long eventId);

    public List<Reclamation> findByUserId(String userId);

    public List<Reclamation> findreclamationbyeventidanduserid(String userid, Long eventid);

    public void deletereclamation(Long IdRec);
}