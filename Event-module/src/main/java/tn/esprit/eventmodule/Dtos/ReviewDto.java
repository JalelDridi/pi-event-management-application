package tn.esprit.eventmodule.Dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
public class ReviewDto {

    private long reviewID;
    private String userID;
    private Long eventID;
    private Integer rating;
    private String content;
    private OffsetDateTime dateSubmitted;
}
