package tn.esprit.review_module.repositories;

import tn.esprit.review_module.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findReviewByReviewID(Long id);
    // Review DeleteReview(Review review);
}
