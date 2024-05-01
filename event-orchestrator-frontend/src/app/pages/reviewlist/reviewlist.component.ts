import { Component, OnInit } from '@angular/core';
import { Review } from '../../ReviewModels/Review.Model';
import { ReviewService } from '../../reviewservices/review.service';
import { BadWordsFilterService } from '../../reviewservices/badwordsfilter.service';
import Swal from 'sweetalert2';
import { DatePipe } from '@angular/common';
import { CustomDatePipe } from 'src/app/custom-date.pipe';

@Component({
    selector: 'app-review-list',
    templateUrl: './reviewlist.component.html',
    styleUrls: ['./reviewlist.component.css']
})
export class ReviewlistComponent implements OnInit {
    currentDate = new Date();
    formattedDate = this.datePipe.transform(this.currentDate, 'yyyy-MM-ddTHH:mm:ss.SSSZ');
    reviews: Review[] = [];
    averageRating: number = 0;
    totalReviews: number = 0;
    userId: string = '268';
    editingReviewId?: number | null;
    private _updatedReview: Review | null = null;

    constructor(
        private reviewService: ReviewService,
        private filterService: BadWordsFilterService,
        private datePipe: DatePipe
    ) { }

    ngOnInit(): void {
        this.loadReviews();
    }

    loadReviews(): void {
        const eventID = 89;
        this.reviewService.getReviewsByEventID(eventID).subscribe(reviews => {
            this.reviews = reviews;
            this.calculateAverageRating();
        });
    }

    calculateAverageRating(): void {
        let totalRating = 0;
        this.totalReviews = this.reviews.length;
        for (let review of this.reviews) {
            totalRating += review.rating;
        }
        if (this.totalReviews > 0) {
            this.averageRating = totalRating / this.totalReviews;
        }
    }

    getStarRating(): string {
        const roundedRating = Math.round(this.averageRating * 2) / 2;
        return '★'.repeat(Math.floor(roundedRating)) + '☆'.repeat(5 - Math.ceil(roundedRating));
    }

    deleteReview(reviewId: number): void {
        Swal.fire({
            title: 'Are you sure?',
            text: 'You will not be able to recover this review!',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel',
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                this.reviewService.deleteReview(reviewId).subscribe(
                    () => {
                        this.reviews = this.reviews.filter(review => review.reviewID !== reviewId);
                        this.calculateAverageRating();
                    },
                    error => {
                        console.error('Error deleting review:', error);
                    }
                );
            }
        });
    }

    editReview(review: Review): void {
        this.currentDate = new Date();
        this.formattedDate = this.datePipe.transform(this.currentDate, 'yyyy-MM-ddTHH:mm:ss.SSSZ');

        if (this.editingReviewId === review.reviewID) {
            this.editingReviewId = null;
        } else {
            this.editingReviewId = review.reviewID;
            if (!this._updatedReview) {
                this._updatedReview = { ...review, content: review.content ?? '', rating: review.rating ?? 0, dateSubmitted: this.formattedDate ?? '' };
            }
            this._updatedReview.content = review.content ?? '';
            this._updatedReview.rating = review.rating ?? 0;
        }
    }

    cancelEdit(): void {
        this.editingReviewId = null;
        this._updatedReview = null;
    }

    saveUpdatedReview(reviewId: number): void {
        if (this._updatedReview) {
            if (this.filterService.containsBadWords(this._updatedReview.content)) {
                return;
            }
            this.reviewService.updateReview(reviewId, this._updatedReview).subscribe(
                () => {
                    this.editingReviewId = null;
                    this.loadReviews();
                },
                error => {
                    console.error('Error updating review:', error);
                }
            );
        }
    }

    get updatedReview(): Review | null {
        return this._updatedReview;
    }

    set updatedReview(value: Review | null) {
        this._updatedReview = value;
    }
}
