package tn.esprit.adminmicroservice.Dto;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfUserDto {
    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean statusUser;
}
