import { Component } from '@angular/core';
import { ResourceTypeService } from './resource-type.service';
import { Router } from '@angular/router';
import { ResourceType } from './resource-type';

@Component({
  selector: 'app-resource-type',
  templateUrl: './resource-type.component.html',
  styleUrls: ['./resource-type.component.css']
})
export class ResourceTypeComponent {

  resourceTypes: ResourceType[] =[];
  

  constructor(private resourceTypeService: ResourceTypeService, private router: Router) { }

  ngOnInit() {
    this.getResourceTypes();
    }
    getResourceTypes(){
      this.resourceTypeService.getResourceTypes().subscribe(
        res => {
          console.log(res); // Log the response to the console
          this.resourceTypes = res as ResourceType[]; // Assign the response to your posts array
        },
        error => {
          console.error(error);
          // Log any errors to the console
        }
      );
    }
    deleteResourceType(id: number) {
      this.resourceTypeService.deleteResourceType(id)
        .subscribe(
          data => {
            console.log(data);
            this.getResourceTypes();
          },
          error => console.log(error));
    }
    showResources(resourceTypeID: number) {
      this.router.navigate(['/resources-by-type', resourceTypeID]);
    }
    
}
