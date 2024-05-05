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
 
//   resource: Resource;
//   resourceForm: FormGroup;
//   resourceTypeID: number;
//   resourceTypes : ResourceType;

//   constructor(private route: ActivatedRoute, private fb: FormBuilder ,private resourceService: ResourceService,private router: Router, private resourceTypeService : ResourceTypeService) {
//     this.resourceForm = this.fb.group({
//       resourceName: ['', Validators.required],
//       isAvailable: [false],
//       date: [null, Validators.required],
//       resourceTypeID:1,
//       // Autres champs du formulaire
//     });
//     this.resourceTypeID = 1;
//    }

//   ngOnInit(): void {
//     this.route.params.subscribe(params => {
//       const resourceID = +params['resourceID'];
//       this.loadResource(resourceID);
    
//     });


// }



// loadResource(resourceID: number): void {
//   this.resourceService.getResource(resourceID).subscribe(resource => {
//     this.resource = resource;
//     this.resourceForm.patchValue({
//       resourceName: resource.resourceName,
//       isAvailable: resource.isAvailable,
//       date: resource.date,
//       resourceTypeID: resource.resourceType.resourceTypeID // Assuming resourceType has resourceTypeID
//     });
//   });
// }

//   // getResource(resourceID: number): void {
   
//   //   this.resourceService.getResource(resourceID)
//   //   .subscribe(resource => {
//   //     this.resource = resource;
//   //   });
//   // }

//   updateResource(): void {
//     if (this.resourceForm.valid) {
//       const updatedResource: Resource = this.resourceForm.value;
//       updatedResource.resourceTypeID = this.resourceTypeID;
//        this.resourceService.updateResource(updatedResource, this.resourceTypeID).subscribe
//       ((response) => {console.log('resource updated successfully',response);
//          this.router.navigate(['/resources'])
//         }, (error) => {console.log(error)});
//     }
//   }

resourceId: number;
resource: Resource;

constructor(private route: ActivatedRoute, private router: Router, private resourceService: ResourceService,) { }

ngOnInit(): void {
  this.resourceId = this.route.snapshot.params['id']; // Assuming you're passing resource ID in the route
  this.fetchResource();
}
fetchResource() {
  // Call your service to get the resource by ID
    this.resourceService.getResource(this.resourceId).subscribe((res: Resource) => {
    this.resource = res;
  });
}

updateResource() {
  this.resourceService.updateResource(this.resource, this.resource.resourceTypeID).subscribe(() => {
    // Handle success, maybe show a message
    this.router.navigate(['/resources']); // Redirect to resource list after successful update
  }, error => {
    // Handle error, maybe show an error message
    console.error(error);
  });
}
}
