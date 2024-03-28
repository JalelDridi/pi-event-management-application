package com.event.event_pi.Entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter

public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User User;
    @ManyToOne
    private Event Event ;





}
