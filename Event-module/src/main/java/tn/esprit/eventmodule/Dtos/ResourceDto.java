package tn.esprit.eventmodule.Dtos;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto {
    private Long resourceID;
    private String resourceName;
    private Boolean isAvailable;

}
