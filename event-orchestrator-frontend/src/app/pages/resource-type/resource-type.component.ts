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
          console.log(res); 
          this.resourceTypes = res as ResourceType[]; 
        },
        error => {
          console.error(error);
          
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
    
    onUpdate(resourceTypeID: number): void {
      this.router.navigate(['/updateResourceType', resourceTypeID]);
    }
}
