package tn.esprit.review_module.Dtos;


import lombok.*;

import java.time.OffsetDateTime;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEventDto {

    private String userID;
    private Long eventID;
    private Integer rating;
    private String content;
    private OffsetDateTime dateSubmitted;
}
