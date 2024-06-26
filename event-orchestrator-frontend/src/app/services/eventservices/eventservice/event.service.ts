import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, throwError } from 'rxjs';
//import * as MyEventModel from '../../../../../../../../../../../Desktop/AngularPi - Copie/src/app/models/event.model';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})

export class EventService {
private baseUrl = 'http://apigateway:8060' ;
  constructor(private http:HttpClient,private sanitizer: DomSanitizer) { }
  GetEventList(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/Event/getall`);
  }

  GetEventDetails(eventId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/Event/getEventById/${eventId}`).pipe(
      catchError((error) => {
        console.error('Error fetching event details:', error);
        return throwError('Failed to fetch event profile');
      })
    );
  }
  addEvent(formData: FormData, userId: String): Observable<any> {
    return this.http.post(`http://apigateway:8060/Event/addevent/${userId}`, formData);
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

  getUpcomingEvents(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/Event/upcoming`);
  }

  exporteventsToExcel(): Observable<Blob> {
    return this.http.get('http://apigateway:8060/Event/export-events', { responseType: 'blob' });
  }


  getEventsByType(type: string): Observable<any[]> {
   return this.http.get<any[]>(`${this.baseUrl}/Event/events/${type}`);
  }
  getAllCategories():Observable<any>{
    return this.http.get('http://apigateway:8060/Event/eventtypes')
  }

  AddParticipation(eventId: number, userId : String ) : Observable<any>{
    let params = new HttpParams().set('userId', userId.toString()).set('eventId', eventId.toString());
    return this.http.post(`${this.baseUrl}/Event/add-participation`, null, {params: params});
  }

  getImage(eventId: number): Observable<SafeUrl> {
    const url = `http://apigateway:8060/events/image/${eventId}`; // Use your actual backend URL
    return this.http.get(url, { responseType: 'blob' }).pipe(
        map(blob => {
            var urlToBlob = window.URL.createObjectURL(blob); // create URL from blob
            return this.sanitizer.bypassSecurityTrustUrl(urlToBlob); // sanitize the URL
        })
    );
}



  }






















