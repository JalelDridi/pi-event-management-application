import { Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Resource } from '../resource-list/resource';
import { ResourceService } from '../resource-list/resource.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { ResourceType } from '../resource-type/resource-type';
import { ResourceTypeService } from '../resource-type/resource-type.service';

@Component({
  selector: 'app-add-resource',
  templateUrl: './add-resource.component.html',
  styleUrls: ['./add-resource.component.css']
})
export class AddResourceComponent {

  submitted = false;
  resourceTypes: ResourceType[];
  resource: Resource= new Resource();
  resourceForm : FormGroup;
  resourceTypeID: number;

  
  constructor(private resourceService : ResourceService , private router : Router , private fb: FormBuilder , private resourceTypeService: ResourceTypeService) { 
    this.resourceForm = this.fb.group({
      resourceName: ['', Validators.required],
      date: [null, Validators.required],
      isAvailable:[false] ,
      resourceTypeID: 1,
  
    });
    this.resourceTypeID = 1;
  }
  ngOnInit() {
    this.getResourceTypes();
    }
    getResourceTypes(){
      this.resourceTypeService.getResourceTypes().subscribe(
        res => {
          console.log(res); // Log the response to the console
          this.resourceTypes = res as ResourceType[]; 
        },
        error => {
          console.error(error);
          // Log any errors to the console
        }
      );
    }
  

  addResource() {
    if (this.resourceForm.valid) {
      const newResource = { ...this.resourceForm.value };
      console.log("resourceTypeID", this.resourceTypeID);
      this.resourceService.addResource(newResource, this.resourceTypeID).subscribe(
        (response) => {
          // Afficher une alerte de succès avec SweetAlert
          Swal.fire({
            icon: 'success',
            title: 'resource ajouté avec succès!',
            showConfirmButton: false,
            timer: 1500 // Fermer automatiquement l'alerte après 1.5 seconde
          });
          setTimeout(() => {
            this.router.navigate(['/resources']);
          }, 1500);
        },
        (error) => {
          console.error('Erreur lors de l\'ajout du resource : ', error);
        }
      );
    }
  }





}
