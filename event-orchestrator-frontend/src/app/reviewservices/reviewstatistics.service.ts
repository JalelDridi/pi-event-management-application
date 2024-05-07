import { Injectable } from '@angular/core';
import { Review } from '../ReviewModels/Review.Model';
 
@Injectable({
  providedIn: 'root'
})
export class ReviewStatisticsService {


public getEventIDWithHighestAvgRating(reviews: Review[]): number | null {
    if (reviews.length === 0) return null;
    const eventAverageRatings = this.calculateEventAverageRatings(reviews);
    const eventID = Object.keys(eventAverageRatings).reduce((a, b) => eventAverageRatings[a] > eventAverageRatings[b] ? a : b);
    return parseInt(eventID);
  }

  public getEventIDWithLowestAvgRating(reviews: Review[]): number | null {
    if (reviews.length === 0) return null;
    const eventAverageRatings = this.calculateEventAverageRatings(reviews);
    const eventID = Object.keys(eventAverageRatings).reduce((a, b) => eventAverageRatings[a] < eventAverageRatings[b] ? a : b);
    return parseInt(eventID);
  }

  public calculateEventAverageRatings(reviews: Review[]): { [eventID: number]: number } {
    const eventRatingsSum = {};
    const eventReviewCounts = {};
    reviews.forEach(review => {
      eventRatingsSum[review.eventID] = (eventRatingsSum[review.eventID] || 0) + review.rating;
      eventReviewCounts[review.eventID] = (eventReviewCounts[review.eventID] || 0) + 1;
    });

    const eventAverageRatings = {};
    for (const eventId in eventRatingsSum) {
      eventAverageRatings[eventId] = eventRatingsSum[eventId] / eventReviewCounts[eventId];
    }
    return eventAverageRatings;
  }

  public getUserWithMostReviews(reviews: Review[]): string | null {
    if (reviews.length === 0) return null;
    const userReviewsCount = reviews.reduce((acc, curr) => {
      acc[curr.userID] = (acc[curr.userID] || 0) + 1;
      return acc;
    }, {});
    return Object.keys(userReviewsCount).reduce((a, b) => userReviewsCount[a] > userReviewsCount[b] ? a : b);
  }

  public getMaxReviewsByEvent(reviews: Review[]): number | null {
    if (reviews.length === 0) return null;
    const eventReviewsCount = reviews.reduce((acc, curr) => {
      acc[curr.eventID] = (acc[curr.eventID] || 0) + 1;
      return acc;
    }, {} as { [eventId: number]: number });
    const maxReviews = Math.max(...Object.values(eventReviewsCount));
    return parseInt(Object.keys(eventReviewsCount).find(key => eventReviewsCount[key] === maxReviews));
  }
  

  public getEventWithLowestReviews(reviews: Review[]): number | null {
    if (reviews.length === 0) return null;
    const eventReviewsCount = reviews.reduce((acc, curr) => {
      acc[curr.eventID] = (acc[curr.eventID] || 0) + 1;
      return acc;
    }, {});
  
    const minReviews = Math.min(...Object.values(eventReviewsCount).map(val => Number(val)));
    return parseInt(Object.keys(eventReviewsCount).find(key => eventReviewsCount[key] === minReviews));
  }

  
  public orderEventsByAvgReview(reviews: Review[]): { eventId: number; avgRating: number }[] {
    const eventAverageRatings = this.calculateEventAverageRatings(reviews);
    const eventAverageRatingsArray = Object.keys(eventAverageRatings).map(eventId => ({
      eventId: parseInt(eventId),
      avgRating: eventAverageRatings[eventId]
    }));
    eventAverageRatingsArray.sort((a, b) => b.avgRating - a.avgRating);
    return eventAverageRatingsArray;
  }
  
}