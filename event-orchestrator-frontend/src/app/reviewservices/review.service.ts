import { Review } from '../ReviewModels/Review.Model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root' 
})
export class ReviewService {
  private baseUrl = 'http://localhost:8090/review';

  constructor(private http: HttpClient) { }

  createReview(review: Review): Observable<Review> {
    return this.http.post<Review>(`${this.baseUrl}/addreview`, review);
  }


  getReview(reviewID: number): Observable<Review> {
    return this.http.get<Review>(`${this.baseUrl}/${reviewID}`);
  }


  updateReview(reviewID: number, value: any): Observable<Review> {
    return this.http.put<Review>(`${this.baseUrl}/updatereview/${reviewID}`, value);
  }

  deleteReview(reviewID: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/deletereview/${reviewID}, { responseType: 'text' }`);
  }

  getReviewsList(): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.baseUrl}/getreviews`);
  }

  getReviewsByEventID(eventID: number): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.baseUrl}/getreviewsbyevent/${eventID}`);
  }

  getReviewsByUserID(userID: string): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.baseUrl}/getreviewsbyuserid/${userID}`);
  }
}