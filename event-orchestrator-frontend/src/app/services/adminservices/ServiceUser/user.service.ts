import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
 
  private baseUrl = 'http://localhost:8092';


  constructor(private http:HttpClient) { }


  //L API a travers l backends jebneh bel fonction hedhy
  getUserList(): Observable<any> {
    const url = `${this.baseUrl}/admin/ListFinal`;
    return this.http.get<any>(url);
  }



  findAllconfUsers(): Observable<any> {
    const url = `${this.baseUrl}/admin/UsersConfirmer`;
    return this.http.get<any>(url);
  }
 


  deleteUser(id: string): Observable<any> {
    // Make sure to pass the id as part of the URL
    return this.http.post(`${this.baseUrl}/admin/DeleteUser/${id}`, null); ////PathVairable
  }

  AcceptUserCnx(id:String): Observable<any> {
    return this.http.post(`${this.baseUrl}/admin/accept/${id}`, null);  ///PathVairable
  
  }


  pourcentageUsersAuth(): Observable<any> {
    const url = `${this.baseUrl}/admin/satistique`;
    return this.http.get<any>(url);
  }

  getPercentageUsersAuth(): Observable<number> {
    return this.http.get<number>('/api/statistics/satistique');
  }



}
