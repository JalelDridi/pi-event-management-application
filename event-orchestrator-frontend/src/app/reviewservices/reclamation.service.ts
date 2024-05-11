import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reclamation } from '../ReviewModels/Reclamation.Model';

@Injectable({
  providedIn: 'root'
})
export class ReclamationService {

  private apiUrl = 'http://reviewservice/reclamations';

  constructor(private http: HttpClient) { }

  addReclamation(reclamation: Reclamation): Observable<Reclamation> {
    return this.http.post<Reclamation>(`${this.apiUrl}/addreclamation`, reclamation);
  }

  getAllReclamations(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/getreclamations`);
  }

  getReclamationsByEventId(eventId: number): Observable<Reclamation[]> {
    return this.http.get<Reclamation[]>(`${this.apiUrl}/getreclamationsbyeventid/${eventId}`);
  }

  getReclamationsByUserId(userId: string): Observable<Reclamation[]> {
    return this.http.get<Reclamation[]>(`${this.apiUrl}/getreclamationsbyuserid/${userId}`);
  }
}
