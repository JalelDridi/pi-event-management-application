import { Component } from '@angular/core';
import { ResourceService } from '../resource-list/resource.service';
import { Resource } from '../resource-list/resource';
import { Router } from '@angular/router';
import { ResourceTypeService } from '../resource-type/resource-type.service';
import { ResourceType } from '../resource-type/resource-type';


@Component({
  selector: 'app-ressources',
  templateUrl: './ressources.component.html',
  styleUrls: ['./ressources.component.css']
})
export class RessourcesComponent {

  resources: Resource[] =[];
  resourceTypeID: number;


  constructor(private resourceService: ResourceService, private router: Router , private resourceTypeService :ResourceTypeService) { }

ngOnInit() {
this.getResources();
}
getResources(){
  this.resourceService.getResources().subscribe(
    res => {
      console.log(res); // Log the response to the console
      this.resources = res as Resource[]; // Assign the response to your posts array
    },
    error => {
      console.error(error);
      // Log any errors to the console
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
