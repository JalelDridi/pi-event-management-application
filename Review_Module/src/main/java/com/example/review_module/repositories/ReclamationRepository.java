package com.example.review_module.repositories;

import com.example.review_module.entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation, Long> {
}
