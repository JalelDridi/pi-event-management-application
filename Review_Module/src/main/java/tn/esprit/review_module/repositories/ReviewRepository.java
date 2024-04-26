package tn.esprit.review_module.repositories;

import tn.esprit.review_module.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findReviewByReviewID(Long id);
    List<Review> findReviewsByUserID(String id);
    List<Review> findReviewsByEventID(Long id);
    List<Review> findReviewsByUserIDAndEventID(String userid , Long eventid);

    List<Review> findReviewsByRating(Integer rating);


    //int countReviewByEventID(Long eventid);

    List<Review>findReviewsByDateSubmittedBetween(LocalDate a , LocalDate b);
    List<Review> findReviewsByRatingLessThanEqual(Integer rating);

    List<Review> findReviewsByRatingBetween(Integer a,Integer b);

}