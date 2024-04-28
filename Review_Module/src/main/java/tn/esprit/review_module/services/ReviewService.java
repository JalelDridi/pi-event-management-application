package tn.esprit.review_module.services;

import tn.esprit.review_module.entities.Review;
import java.util.List;

public interface ReviewService {
    public Review addReview(Review review);

    public Review findreviewByreviewid(Long id);
    public List<Review> getAllReviews();

    public Review UpdateReview(Review review,long id);

    public List<Review> findReviewsbyEventID(Long eventid);

    public  void deleteReview(Long id);

}