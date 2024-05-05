package tn.esprit.review_module.Dtos;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ReviewUserDto {

    private String userID;
    private String firstName;
    private String lastName;
    private String email;
}
