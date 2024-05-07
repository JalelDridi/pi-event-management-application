package tn.esprit.eventmodule.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="EVENTS")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId ;
    private String  Name ;
    private String description;
    private String etat; // Accepté | En cours - refusé


    private Date startDate ;
    private Date endDate ;
    @Enumerated ( EnumType.STRING)
    @Column
    private EventType type;
    private String Club ;
    @Column
    @Enumerated ( EnumType.STRING)
    private StatusType status;
    @Column(name = "image", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] image;
    @Column(name = "image1", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] image1;
    private Float  lng ;
    private Float  lat ;


    @OneToMany(mappedBy = "event")
    private List<SelectedResources> selectedResources;




}
