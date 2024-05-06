import { Component } from '@angular/core';
import { Resource } from '../resource-list/resource';
import { ResourceService } from '../resource-list/resource.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ResourceType } from '../resource-type/resource-type';
import { ResourceTypeService } from '../resource-type/resource-type.service';

@Component({
  selector: 'app-update-resource',
  templateUrl: './update-resource.component.html',
  styleUrls: ['./update-resource.component.css']
})
export class UpdateResourceComponent {
  resourceId: number;
  updatedResource: Resource = new Resource();
  resourceTypes: ResourceType[] = [];

  constructor(private route: ActivatedRoute, private router: Router, private resourceService: ResourceService,private resourceTypeService: ResourceTypeService) { }

  ngOnInit(): void {
    this.resourceId = +this.route.snapshot.params['resourceTypeID']; // Get resource ID from URL
    this.fetchResource(); // Fetch resource data when component initializes
    this.fetchResourceTypes(); // Fetch resource types
  }

  fetchResource(): void {
    this.resourceService.getResource(this.resourceId).subscribe(
      resource => {
        this.updatedResource = resource; // Assign fetched resource to updatedResource
      },
      error => {
        console.log('Error fetching resource:', error);
      }
    );
  }

  fetchResourceTypes(): void {
    this.resourceTypeService.getResourceTypes().subscribe(
      types => {
        this.resourceTypes = types; // Assign fetched resource types to resourceTypes
      },
      error => {
        console.log('Error fetching resource types:', error);
      }
    );
  }

  updateResource(): void {
    const resourceTypeID = this.updatedResource.resourceTypeID; // Get the resourceTypeID
    this.resourceService.updateResource(this.updatedResource, resourceTypeID).subscribe(
      updatedResource => {
        console.log('Resource updated successfully:', updatedResource);
        // Optionally, navigate to a different route after successful update
        this.router.navigate(['/resources']);
      },
      error => {
        console.error('Error updating resource:', error);
      }
    );
  }
}
