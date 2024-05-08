import {Component, OnInit} from '@angular/core';
import { Resource } from './resource';
import { ResourceService } from './resource.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-resource-list',
  templateUrl: './resource-list.component.html',
  styleUrls: ['./resource-list.component.css']
})
export class ResourceListComponent implements OnInit{

  resources: Resource[] =[];
  

  constructor(private resourceService: ResourceService, private router: Router) { }

ngOnInit() {
this.getResources();
}
getResources(){
  this.resourceService.getResources().subscribe(
    res => {
      console.log(res); 
      this.resources = res as Resource[]; 
    },
    error => {
      console.error(error);
    
    }
  );
}

deleteResource(id: number) {
  this.resourceService.deleteResource(id)
    .subscribe(
      data => {
        console.log(data);
        this.getResources();
      },
      error => console.log(error));
}

updateResource(resource:Resource){
  this.router.navigate(['update', resource]);
}

}
