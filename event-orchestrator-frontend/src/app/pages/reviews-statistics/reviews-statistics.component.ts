import { ReviewService } from './../../reviewservices/review.service';
import { Review } from './../../ReviewModels/Review.Model';
import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts';
import { ReviewStatisticsService } from 'src/app/reviewservices/reviewstatistics.service';

@Component({
  selector: 'app-review-statistics',
  templateUrl: './reviews-statistics.component.html',
  styleUrls: ['./reviews-statistics.component.css']
})
export class ReviewStatisticsComponent implements OnInit {
  
  chartOptions: EChartsOption;
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
      },
      error => {
        console.error('Error fetching reviews:', error);
      }
    );
  }

  initializeChart(): void {
    const orderedEvents = this.reviewStatsService.orderEventsByAvgReview(this.reviews);

    const eventIDs = orderedEvents.map(event => event.eventId.toString()); // Convert event IDs to strings
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
}
