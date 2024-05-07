package tn.esprit.eventmodule.Entities;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class SelectedResources implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Event event;
}
