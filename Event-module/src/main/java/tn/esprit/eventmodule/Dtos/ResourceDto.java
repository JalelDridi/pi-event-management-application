package tn.esprit.eventmodule.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data

public class ResourceDto {
    private Long resourceID;
    private String resourceName;
    private ResourceTypeDto resourceType;

}
