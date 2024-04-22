package org.example.microservicenadine.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StatusRessources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  Long id_Ressource;
    private  String type;
    private int statusReservation;
}
