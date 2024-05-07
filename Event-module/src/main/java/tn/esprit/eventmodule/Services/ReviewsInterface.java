package tn.esprit.eventmodule.Services;

import reactor.core.publisher.Flux;
import tn.esprit.eventmodule.Dtos.ReviewDto;

import java.util.List;

public interface ReviewsInterface {
    public List<ReviewDto> findReviewsByUserIdAndEventId(String userId, Long eventId);
    List<ReviewDto> getReviewsByEventId(Long eventId);
}
