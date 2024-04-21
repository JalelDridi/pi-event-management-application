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
@Table(name = "resource")
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceID;

    private String resourceName;

    private Boolean isAvailable;
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "resource_type_id")
    private ResourceType resourceType;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Reservation> reservations;
}
