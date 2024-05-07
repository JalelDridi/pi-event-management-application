package tn.esprit.eventmodule.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import tn.esprit.eventmodule.Dtos.ReviewDto;
import tn.esprit.eventmodule.Services.ReviewsInterface;

import java.util.Collections;
import java.util.List;
@Service
public class ReviewsImpl implements ReviewsInterface {

    private  RestTemplate restTemplate;
    private final String reviewServiceUrl = "http://localhost:8090/api/review";

    @Autowired
    public void ReviewService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ReviewDto> findReviewsByUserIdAndEventId(String userId, Long eventId) {
        String url = reviewServiceUrl + "/getreviewsbyuseridandeventid/{userId}/{eventId}";
        try {
            ResponseEntity<List<ReviewDto>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ReviewDto>>() {},
                    userId,
                    eventId
            );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }//http://localhost:8080/review/getreviewsbyevent/{{id}}
    @Override
    public List<ReviewDto> getReviewsByEventId(Long eventId) {
        String url = reviewServiceUrl + "/getreviewsbyevent/{id}";
        ResponseEntity<List<ReviewDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReviewDto>>() {},
                eventId
        );
        return response.getBody() != null ? response.getBody() : Collections.emptyList();
    }
}
