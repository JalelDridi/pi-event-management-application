import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserFinalService {

  private baseUrl = 'http://localhost:8092';

  constructor(private http:HttpClient) { 


  }

  findAllconfUsers(): Observable<any> {
    const url = `${this.baseUrl}/admin/UsersConfirmer`;
    return this.http.get<any>(url);
  }


  rechercheByfonction(): Observable<any> {
    const url = `${this.baseUrl}/admin/rechercheByfonction`;
    return this.http.get<any>(url);
  }


  sendUpdateEmail(recipientEmail: string, mail: string): Observable<any> {
    const url = `${this.baseUrl}/admin/mailSender?recipientEmail=${recipientEmail}&mail=${mail}`;
    return this.http.get<any>(url);
  }
}
