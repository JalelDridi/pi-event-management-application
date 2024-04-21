package com.esprit.pidev.resourcemodule.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "reservations")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationID;

    @Temporal(TemporalType.DATE)
    private Date startDate;
    private Date endDate;

    private Boolean isValid;

    @ManyToOne
    @JoinColumn(name = "resourceID")
    @JsonIgnore
    private Resource resource;

}
