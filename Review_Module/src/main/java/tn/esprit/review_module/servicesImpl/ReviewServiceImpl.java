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

    @Override
    public List<Review> findReviewsbyEventID(Long eventid) {
        return reviewRepository.findReviewsByEventID(eventid);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findReviewByReviewID(id);
        if (review != null) {
            reviewRepository.delete(review);
        }
    }

    @Override
    public List<Review> findReviewbyuseridandeventid(String userId, Long eventId) {
        return reviewRepository.findReviewsByUserIDAndEventID(userId, eventId);
    }

   /* @Override
    public void affecterReviewToUser(Long UserId, Long reviewId) {
        Review review = reviewRepository.findById(UserId).orElse(null);
        Participation participation = new Participation();
        participation.setUserID(userID);
        participation.setEventId(eventId);
        participationDao.save(participation);
    }*/


}