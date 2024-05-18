import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginComponent } from 'src/app/pages/login/login.component';

@Injectable({
  providedIn: 'root'
})
export class AdminEventListService {

  private baseUrl = 'http://localhost:8092';


  constructor(private http:HttpClient) { }

    //L API a travers l backends jebneh bel fonction hedhy
    getEventList(): Observable<any> {
      const url = `${this.baseUrl}/admin/listEvents`;
      return this.http.get<any>(url);
    }

    deleteEvent(id: number): Observable<any> {
      // Make sure to pass the id as part of the URL
      return this.http.post(`${this.baseUrl}/admin/refuserEvent/${id}`, null); ////PathVairable
    }

    AcceptEvent(id:number): Observable<any> {
      return this.http.post(`${this.baseUrl}/admin/acceptEvent/${id}`, null);  ///PathVairable

    }
}
