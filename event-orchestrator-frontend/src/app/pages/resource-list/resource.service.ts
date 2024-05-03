import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Resource } from './resource';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  
  private baseUrl='http://localhost:8093/api/resources';
 

  constructor(private httpClient: HttpClient) { }

  getResource(resourceID: number): Observable<any> {
    return this.httpClient.get(this.baseUrl+'/'+resourceID);
  }
  getResources(): Observable<Resource[]> {
    return this.httpClient.get<Resource[]>(this.baseUrl + '/all-resources');
  
  }

  addResource(resource: any): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/addResource`, resource);
  }
  deleteResource(resourceID: number): Observable<any> {
    return this.httpClient.delete(this.baseUrl+'/deleteResource/'+resourceID);
  }

  updateResource(resourceID: number,resource: any): Observable<Resource> {
    // return this.httpClient.put(`${this.baseUrl}/updateResource/${resourceID}`, resource);
    const url = `${this.baseUrl}/updateResource/${resourceID}`;
    return this.httpClient.put<Resource>(url, resource);
  }
}

