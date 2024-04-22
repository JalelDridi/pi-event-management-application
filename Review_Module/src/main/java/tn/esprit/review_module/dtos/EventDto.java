package tn.esprit.review_module.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@Data
public class EventDto {

    public class Event {
        private Long eventId;
        private String Name;
    }
}
