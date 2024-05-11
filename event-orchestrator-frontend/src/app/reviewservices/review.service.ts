import { Review } from '../ReviewModels/Review.Model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  private baseUrl = 'http://reviewservice:8090/review';
  private readonly eventServiceUrl = 'http:/eventservice:8089/events';

  constructor(private http: HttpClient) { }



  getEventTypeForReview(review: Review): Observable<string> {
    const url = `http://eventservice/Event/getEventById/${review.eventID}`;
    return this.http.get<Event>(url).pipe(
        map((event: Event) => event.type),
        catchError((error: any) => {
            console.error('Error fetching event type for review:', error);
            return throwError(error);
        })
    );
}


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
    return this.http.delete(`${this.baseUrl}/deletereview/${reviewID}`, { responseType: 'text' });
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
