package tn.esprit.eventmodule.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.PrivateKey;
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
    private String Image;
    private Date startDate ;
    private Date endDate ;
    @Enumerated ( EnumType.STRING)
    @Column
    private EventType type;
    private String Club ;
    @Column
    @Enumerated ( EnumType.STRING)
    private StatusType status;
    private String Rating  ;


}
