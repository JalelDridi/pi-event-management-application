package com.esprit.pidev.resourcemodule.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="resourceType")

public class ResourceType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resouceTypeName;

    @OneToMany(mappedBy = "resourceType", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Resource> resources;


}
