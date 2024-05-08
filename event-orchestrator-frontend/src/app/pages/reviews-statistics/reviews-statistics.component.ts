import { ReviewService } from './../../reviewservices/review.service';
import { Review } from './../../ReviewModels/Review.Model';
import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts';
import { ReviewStatisticsService } from 'src/app/reviewservices/reviewstatistics.service';
import { forkJoin, Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Component({
  selector: 'app-review-statistics',
  templateUrl: './reviews-statistics.component.html',
  styleUrls: ['./reviews-statistics.component.css']
})
export class ReviewStatisticsComponent implements OnInit {
  highestAvgRatingEventID: number | null = null;
  lowestAvgRatingEventID: number | null = null;
  userWithMostReviews: string | null = null;
  maxReviewsByEvent: number | null = null;
  eventWithLowestReviews: number | null = null;
  chartOptions: EChartsOption;
  chartOptions2: EChartsOption;
  chartOptions3: EChartsOption;
  reviews : Review[] = [];
  constructor(private reviewStatsService: ReviewStatisticsService , private reviewService: ReviewService) {}

  ngOnInit(): void {
    this.loadReviews(); 
  }

  loadReviews(): void {
    this.reviewService.getReviewsList().subscribe(
      reviews => {
        this.reviews = reviews;
        this.initializeChart(); 
        this.initializeRatingDistributionChart();
        this.initializeChart2(); 
        this.calculateOtherStatistics();
      },
      error => {
        console.error('Error fetching reviews:', error);
      }
    );
  }

  initializeChart(): void {
    const orderedEvents = this.reviewStatsService.orderEventsByAvgReview(this.reviews);
    const eventIDs = orderedEvents.map(event => event.eventId.toString()); 
    const avgReviewScores = orderedEvents.map(event => event.avgRating);

    this.chartOptions = {
      title: {
        text: 'Event scores by average rating',
        left: 'center', 
        top: 'bottom' 
      },
      xAxis: {
        type: 'category',
        data: eventIDs 
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: avgReviewScores,
          type: 'line',
          markPoint: { 
            data: avgReviewScores.map((score, index) => ({ name: eventIDs[index], value: score }))
          }
        }
      ]
    };
  }

  initializeRatingDistributionChart(): void {
    const ratingDistribution = this.reviewStatsService.calculateRatingDistribution(this.reviews);
    const ratings = ratingDistribution.map(data => data.rating);
    const counts = ratingDistribution.map(data => data.count);
  
    this.chartOptions2 = {
      title: {
        text: 'Rating Distribution',
        left: 'center', 
        top: 'bottom' 
      },
      xAxis: {
        type: 'category',
        data: ratings.map(rating => rating.toString())
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: counts,
          type: 'bar'
        }
      ]
    };
  }

  initializeChart2(): void {
    const reviewTypesMap: { [eventType: string]: number } = {};
  
    const requests: Observable<string>[] = this.reviews.map(review =>
      this.reviewService.getEventTypeForReview(review).pipe(
        catchError(error => {
          console.error('Error fetching event type for review:', error);
          return throwError(error);
        })
      )
    );
  
    forkJoin(requests).subscribe(
      eventTypes => {
        eventTypes.forEach(eventType => {
          if (eventType) {
            reviewTypesMap[eventType] = (reviewTypesMap[eventType] || 0) + 1;
          }
        });
        this.updateChartData(reviewTypesMap);
      },
      error => {
        console.error('Error fetching event types for reviews:', error);
      }
    );
  }
  

  updateChartData(reviewTypesMap: { [eventType: string]: number }): void {
    const eventTypes = Object.keys(reviewTypesMap);
    const counts = Object.values(reviewTypesMap);
  
    this.chartOptions3 = {
      title: {
        text: 'Review Distribution by Event Type',
        left: 'center',
        top: 'bottom'
      },
      series: [
        {
          type: 'pie',
          data: eventTypes.map((type, index) => ({ name: type, value: counts[index] }))
        }
      ]
    };
  }
  calculateOtherStatistics(): void {
    this.highestAvgRatingEventID = this.reviewStatsService.getEventIDWithHighestAvgRating(this.reviews);
    this.lowestAvgRatingEventID = this.reviewStatsService.getEventIDWithLowestAvgRating(this.reviews);
    this.userWithMostReviews = this.reviewStatsService.getUserWithMostReviews(this.reviews);
    this.maxReviewsByEvent = this.reviewStatsService.getMaxReviewsByEvent(this.reviews);
    this.eventWithLowestReviews = this.reviewStatsService.getEventWithLowestReviews(this.reviews);
  }
  
}
