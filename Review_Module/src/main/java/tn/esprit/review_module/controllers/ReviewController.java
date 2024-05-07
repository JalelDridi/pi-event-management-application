package tn.esprit.review_module.controllers;

import org.springframework.http.ResponseEntity;
import tn.esprit.review_module.entities.Review;
import tn.esprit.review_module.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @PostMapping("/addreview")
    @ResponseBody
    public Review addReview(@RequestBody Review review ){
        return reviewService.addReview(review);
    }

    @GetMapping("/findreview/{id}")
    @ResponseBody
    public Review findReviewbyID(@PathVariable Long id){
        return reviewService.findreviewByreviewid(id);
    }
    @GetMapping("/getreviews")
    @ResponseBody
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }


    @PutMapping("/updatereview/{id}")
    @ResponseBody
    public Review UpdateReview(@RequestBody Review review, @PathVariable Long id){
        return this.reviewService.UpdateReview(review, id);
    }
    @DeleteMapping("/deletereview/{id}")
    @ResponseBody
    public void DeleteReview(@PathVariable Long id){
        this.reviewService.deleteReview(id);
    }

    @GetMapping("/getreviewsbyevent/{id}")
    @ResponseBody
    public  List<Review>findreviewsbyeventid( @PathVariable Long id){
        return reviewService.findReviewsbyEventID(id);
    }


    @GetMapping("/getreviewsbyuseridandeventid/{userid}/{eventid}")
    @ResponseBody
    public List<Review> findReviewbyuseridandeventid(@PathVariable String userid, @PathVariable Long eventid){
        return reviewService.findReviewbyuseridandeventid(userid, eventid);
    }

   /* @GetMapping("/event/{eventId}")
    public ResponseEntity<Long> getStatistiquesParEvent(@PathVariable Long eventId) {
        long statistiquesParEvent = reviewService.getStatistiquesParEvent(eventId);
        return ResponseEntity.ok(statistiquesParEvent);
    }*/



}