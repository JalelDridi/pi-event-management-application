package com.example.review_module.services;

import com.example.review_module.entities.Review;

import java.util.List;

public interface ReviewService {
    public Review addReview(Review review);

    public Review findreviewByreviewid(Long id);
    public List<Review> getAllReviews();

    public Review UpdateReview (Review review);

}
