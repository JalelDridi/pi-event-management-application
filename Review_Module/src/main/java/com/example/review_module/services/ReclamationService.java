package com.example.review_module.services;

import com.example.review_module.entities.Reclamation;
import com.example.review_module.entities.Review;

import java.util.List;

public interface ReclamationService {

    public Reclamation addReclamation(Reclamation reclamation);

    public List<Reclamation> getAllReclamations();
}
