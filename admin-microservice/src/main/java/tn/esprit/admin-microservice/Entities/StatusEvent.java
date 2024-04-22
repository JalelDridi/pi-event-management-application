package org.example.microservicenadine.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class StatusEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id_event;
    private int status;



}
