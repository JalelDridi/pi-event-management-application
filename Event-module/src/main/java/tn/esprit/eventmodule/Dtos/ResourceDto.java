package tn.esprit.eventmodule.Dtos;

import lombok.Data;

@Data
public class ResourceDto {
    private Long resourceID;
    private String resourceName;
    private Boolean isAvailable;

}
