package tn.esprit.review_module.servicesImpl;

import tn.esprit.review_module.entities.Review;
import tn.esprit.review_module.repositories.ReviewRepository;
import tn.esprit.review_module.services.ReviewService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Resource
    private ReviewRepository reviewRepository;

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
    public Review UpdateReview(Review review,long id) {
        Review existingReview = reviewRepository.findReviewByReviewID(id);
        if (existingReview != null){
            existingReview.setRating(review.getRating());
            existingReview.setContent(review.getContent());
            existingReview.setDateSubmitted(review.getDateSubmitted());
            return reviewRepository.save(existingReview);
        }
        return null;
    }


}
