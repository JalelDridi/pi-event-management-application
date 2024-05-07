package tn.esprit.eventmodule.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.eventmodule.Dtos.ReviewDto;
import tn.esprit.eventmodule.ServiceImpl.ReviewsImpl;

import java.util.List;

@RestController
@RequestMapping("/Event/Reviews")
public class ReviewsController {
    @Autowired
    ReviewsImpl reviewsImpl;

    @GetMapping("/{eventId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByUserIdAndEventId(@PathVariable String userId, @PathVariable Long eventId) {
        List<ReviewDto> reviews = reviewsImpl.findReviewsByUserIdAndEventId(userId, eventId);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }
}
