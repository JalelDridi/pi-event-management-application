package tn.esprit.eventmodule.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
public class ResourceTypeDto {
    private String resourceTypeName;

}
