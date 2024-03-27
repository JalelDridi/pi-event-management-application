package com.example.review_module.controllers;

import com.example.review_module.entities.Review;
import com.example.review_module.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;
    @Autowired
    public ReviewController( ReviewService reviewService){

        this.reviewService = reviewService;
    }
    @PostMapping("/addreview")
    public Review addReview(     @DateTimeFormat(pattern = "yyyy-MM-dd")
                                     @RequestBody Review review ){
        return reviewService.addReview((review));
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

    @PutMapping("/updatereview")
    @ResponseBody
    public Review UpdateReview(@RequestBody Review review){
        return this.reviewService.UpdateReview(review);
    }


}
