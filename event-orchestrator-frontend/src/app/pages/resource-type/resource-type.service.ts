import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResourceType } from './resource-type';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class ResourceTypeService {


  private baseUrl= 'http://localhost:8093/api/ResourceTypes';

  constructor(private httpClient : HttpClient) { }


  getResourceTypes(): Observable<ResourceType[]> {
    return this.httpClient.get<ResourceType[]>(this.baseUrl + '/allResourceTypes');

  }


  addResourceType(resourceType: any): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/addResourceType`, resourceType);
  }

  deleteResourceType(resourceTypeID: number): Observable<any> {
    return this.httpClient.delete(this.baseUrl+'/deleteResourceType/'+resourceTypeID);
  }

  updateResourceType(resourceTypeID: number, resourceType: ResourceType): Observable<ResourceType> {
    const url = `${this.baseUrl}/updateResourceType/${resourceTypeID}`;
    return this.httpClient.put<ResourceType>(url, resourceType);
  }

  getResourceType(resourceTypeID: number): Observable<any> {
    return this.httpClient.get(this.baseUrl+'/'+resourceTypeID);
  }
}
