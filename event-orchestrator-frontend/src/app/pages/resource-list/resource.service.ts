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

  addResource(resource: any, resourceTypeID:number): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/addResource/${resourceTypeID.toString()}`, resource);
  }
  deleteResource(resourceID: number): Observable<any> {
    return this.httpClient.delete(this.baseUrl+'/deleteResource/'+resourceID);
  }


  updateResource(resource: Resource, resourceTypeID: number): Observable<Resource> {
    return this.httpClient.put<Resource>(`${this.baseUrl}/updateResource/${resourceTypeID.toString()}`, resource);
  }

  findResourcesByResourceType(resourceTypeID: number): Observable<Resource[]> {
    return this.httpClient.get<Resource[]>(`${this.baseUrl}/findByResourceType/${resourceTypeID.toString()}`);
  }
}

