package tn.esprit.eventmodule.Dtos;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventReviewDto {
    private long reviewID;
    private Integer rating;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSubmitted;
}
