package tn.esprit.eventmodule.ServiceImpl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.eventmodule.Dtos.ResourceDto;
import tn.esprit.eventmodule.Services.ResourceInterface;

import java.util.Collections;
import java.util.List;

@Service
public class ResourceImpl implements ResourceInterface {
    private final RestTemplate restTemplate;
    // Updated base URL to include the API path up to the endpoint
    private final String resourceServiceUrl = "http://localhost:8093/api/resources";

    public ResourceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ResourceDto> findResourcesByResourceType(Long resourceTypeID) {
        // Construct the URL with the resource type ID, adjusted to include the complete path
        String url = resourceServiceUrl + "/findByResourceType/{resourceTypeID}";

        // Perform the GET request using RestTemplate
        ResponseEntity<List<ResourceDto>> response;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ResourceDto>>() {},
                    resourceTypeID
            );
        } catch (Exception e) {
            // Log and handle the exception appropriately
            e.printStackTrace();
            return Collections.emptyList();
        }

        // Return the body of the response
        return response.getBody();
    }


    @Override
    public List<ResourceDto> getAllAvailableResources() {
        String url = resourceServiceUrl + "/getAllAvailableResources";

        try {
            ResponseEntity<List<ResourceDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ResourceDto>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
