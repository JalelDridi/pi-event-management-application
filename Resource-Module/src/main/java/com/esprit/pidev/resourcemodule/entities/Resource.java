package com.esprit.pidev.resourcemodule.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "resource")
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long resourceID;

    String resourceName;

    boolean isAvailable;

    Date date;

    @ManyToOne
    @JoinColumn(name = "resource_type_id")
    private ResourceType resourceType;


}
