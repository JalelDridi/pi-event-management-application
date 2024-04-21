package com.example.review_module.servicesImpl;

import com.example.review_module.entities.Reclamation;
import com.example.review_module.repositories.ReclamationRepository;
import com.example.review_module.repositories.ReviewRepository;
import com.example.review_module.services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReclamationServiceImpl implements ReclamationService {

    private final ReclamationRepository reclamationRepository;
    @Autowired
    public ReclamationServiceImpl(ReclamationRepository reclamationRepository){
        this.reclamationRepository= reclamationRepository;
    }
    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @Override
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }
}
