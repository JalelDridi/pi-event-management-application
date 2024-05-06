import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
import * as MyEventModel from '../../../../../../../../../../../Desktop/AngularPi - Copie/src/app/models/event.model';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})

export class EventService {
private baseUrl = 'http://localhost:8089' ;
  constructor(private http:HttpClient,private sanitizer: DomSanitizer) { }
  GetEventList(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/Event/getall`);
  }

  GetEventDetails(eventId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/Event/getAnEvent/${eventId}`).pipe(
      catchError((error) => {
        console.error('Error fetching event details:', error);
        return throwError('Failed to fetch user profile');
      })
    );
  }
  addEvent(formData: FormData, userId: String): Observable<any> {
    return this.http.post(`http://localhost:8089/Event/addevent/${userId}`, formData, { responseType: 'text' });
}

  editEvent(eventId: number, event: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/Event/edited/${eventId}`, event);

  }
  DeleteEvent(eventId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/Event/deleteevent/${eventId}`);
  }


  GetUserevent(UserId: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/Event/${UserId}/events`);
  }
  getUpcomingEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.baseUrl}/Event/upcoming`);
  }


  getEventsByType(type: string): Observable<any[]> {
   return this.http.get<any[]>(`${this.baseUrl}/Event/events/${type}`);
  }
  getAllCategories():Observable<any>{
    return this.http.get('http://localhost:8089/Event/eventtypes')
  }

  AddParticipation(eventId: number, userid : String ) : Observable<any>{
    return this.http.post(`${this.baseUrl}/Event/add-participation/${userid}/${eventId}`,null)
  }

  getImage(eventId: number): Observable<SafeUrl> {
    const url = `http://localhost:8089/events/image/${eventId}`; // Use your actual backend URL
    return this.http.get(url, { responseType: 'blob' }).pipe(
        map(blob => {
            var urlToBlob = window.URL.createObjectURL(blob); // create URL from blob
            return this.sanitizer.bypassSecurityTrustUrl(urlToBlob); // sanitize the URL
        })
    );
}

  }






















