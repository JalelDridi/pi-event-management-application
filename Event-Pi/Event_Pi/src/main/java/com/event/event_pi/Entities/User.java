package com.event.event_pi.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long userID;

        private String firstName;
        private String lastName;
        private String email;
        private String password;

        private Date dateOfBirth;

        private Role role;
    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL)
    private List<Participant> participants;
}
