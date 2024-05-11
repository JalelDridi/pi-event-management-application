import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Resource } from './resource';
import { Observable, map } from 'rxjs';
import { ResourceType } from '../resource-type/resource-type';
import { ResourceStatistics } from '../ressources/resource-statistics';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {


  private baseUrl='http://resourceservice:8093/api/resources';


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
    return this.httpClient.put<Resource>(`${this.baseUrl}/updateResource/${resourceTypeID}`, resource);
  }

  findResourcesByResourceType(resourceTypeID: number): Observable<Resource[]> {
    return this.httpClient.get<Resource[]>(`${this.baseUrl}/findByResourceType/${resourceTypeID.toString()}`);
  }

  getStatistics(): Observable<ResourceStatistics[]> {
    return this.httpClient.get<ResourceStatistics[]>(this.baseUrl+'/statistics/resource-types');
  }
  searchResources(resourceName: string): Observable<Resource[]> {
    return this.httpClient.get<Resource[]>(`${this.baseUrl}/search?query=${resourceName}`);
  }

}

