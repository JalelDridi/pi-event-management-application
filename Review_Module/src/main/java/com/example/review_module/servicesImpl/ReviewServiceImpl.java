package com.example.review_module.servicesImpl;

import com.example.review_module.entities.Review;
import com.example.review_module.repositories.ReviewRepository;
import com.example.review_module.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewDao){
        this.reviewRepository=reviewDao;
    }

    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review findreviewByreviewid(Long id) {
        return reviewRepository.findReviewByReviewID(id);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review UpdateReview(Review review) {
        return reviewRepository.save(review);
    }


}
