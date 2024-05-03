package tn.esprit.eventmodule.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Entity
public class UserEvents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_events_events",
            joinColumns = @JoinColumn(name = "user_events_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "eventId")
    )
    private List<Event> events = new ArrayList<>();

    private String userID;



}
