package com.example.review_module.repositories;

import com.example.review_module.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findReviewByReviewID(Long id);
    Review DeleteReview(Review review);
}
