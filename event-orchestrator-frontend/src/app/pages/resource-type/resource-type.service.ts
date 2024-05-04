import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResourceType } from './resource-type';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResourceTypeService {

   
  private baseUrl='http://localhost:8093/api/ResourceTypes';

  constructor(private httpClient : HttpClient) { }


  getResourceTypes(): Observable<ResourceType[]> {
    return this.httpClient.get<ResourceType[]>(this.baseUrl + '/allResourceTypes');
  
  }

  addResourceType(resourceType: any): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/addResourceType`, resourceType);
  }
}