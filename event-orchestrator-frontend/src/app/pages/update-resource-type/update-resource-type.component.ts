import { Component } from '@angular/core';
import { ResourceType } from '../resource-type/resource-type';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ResourceTypeService } from '../resource-type/resource-type.service';

@Component({
  selector: 'app-update-resource-type',
  templateUrl: './update-resource-type.component.html',
  styleUrls: ['./update-resource-type.component.css']
})
export class UpdateResourceTypeComponent {
  resourceTypeID: number;
  resourceType: ResourceType;
  resourceTypeForm: FormGroup;
 

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private resourceTypeService: ResourceTypeService
  ) {
    this.resourceTypeForm = this.fb.group({
      resourceTypeName: ['', Validators.required],
      // Add other form fields and validators as needed
    });
  }

  ngOnInit(): void {
    this.resourceTypeID = +this.route.snapshot.paramMap.get('resourceTypeID');
    this.fetchResourceType();
  }

  fetchResourceType(): void {
    this.resourceTypeService.getResourceType(this.resourceTypeID).subscribe(
      resourceType => {
        this.resourceType = resourceType;
        this.resourceTypeForm.patchValue({
          resourceTypeName: resourceType.resourceTypeName,
          // Patch other form fields with resource type data
        });
      },
      error => {
        console.error('Error fetching resource type:', error);
      }
    );
  }

  onSubmit(): void {
    if (this.resourceTypeForm.valid) {
      const updatedResourceType: ResourceType = { resourceTypeID: this.resourceTypeID, ...this.resourceTypeForm.value };
      this.resourceTypeService.updateResourceType(this.resourceTypeID, updatedResourceType).subscribe(
        () => {
          // Handle success, e.g., show a success message and navigate back to resource type list
          this.router.navigate(['/resourceTypesList']);
        },
        error => {
          // Handle error, e.g., show an error message
          console.error('Error updating resource type:', error);
        }
      );
    }
  }
}
