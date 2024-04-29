package tn.esprit.eventmodule.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ResourceReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceID;

    private String resourceName;

    private Boolean isAvailable;
    private long eventId;
}
