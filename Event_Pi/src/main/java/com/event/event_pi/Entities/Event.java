package com.event.event_pi.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId ;
    private String  Name ;
    private Date StartDate ;
    private Date EndDate ;
    @Enumerated ( EnumType.STRING)
    private EventType Type ;
    private String Club ;
    private boolean Status;
    private String Rating  ;
    @OneToMany(mappedBy = "Event", cascade = CascadeType.ALL)
    private List<Participant> participants;


}
