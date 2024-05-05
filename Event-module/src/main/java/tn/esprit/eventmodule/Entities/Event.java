package tn.esprit.eventmodule.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId ;
    private String  Name ;
    private String description;

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
    private Float  lng ;
    private Float  lat ;





}
