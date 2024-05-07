package tn.esprit.eventmodule.Services;

import tn.esprit.eventmodule.Dtos.ResourceDto;

import java.util.List;

public interface ResourceInterface {
    List<ResourceDto> findResourcesByResourceType(Long resourceTypeID);

    List<ResourceDto> getAllAvailableResources();
}
