package com.event.event_pi.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User User;
    @ManyToOne
    private Event Event ;

    public Participant(com.event.event_pi.Entities.User user, com.event.event_pi.Entities.Event event) {
        User = user;
        Event = event;
    }
}
