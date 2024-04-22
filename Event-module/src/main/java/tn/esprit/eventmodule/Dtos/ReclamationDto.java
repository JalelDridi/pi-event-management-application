package tn.esprit.eventmodule.Dtos;

import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class ReclamationDto {
    @Id
    private Long IdRec;
    private LocalDateTime DateReclamation;
}
