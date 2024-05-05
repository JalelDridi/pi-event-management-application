import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ServiceRessourceService {

  private baseUrl = 'http://localhost:8092';


  constructor(private http:HttpClient) { }


  getALLRessources(): Observable<any> {
    const url = `${this.baseUrl}/admin/listeRessources`;
    return this.http.get<any>(url);
  }

  calculateAvailabilityPercentage(): Observable<any> {
    const url = `${this.baseUrl}/admin/pourcentageRessource`;
    return this.http.get<any>(url);
  }




}
