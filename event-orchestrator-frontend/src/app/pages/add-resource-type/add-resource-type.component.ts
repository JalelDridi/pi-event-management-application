import { Component } from '@angular/core';
import { ResourceType } from '../resource-type/resource-type';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ResourceTypeService } from '../resource-type/resource-type.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-resource-type',
  templateUrl: './add-resource-type.component.html',
  styleUrls: ['./add-resource-type.component.css']
})
export class AddResourceTypeComponent {
  submitted = false;
  resourceType: ResourceType = new ResourceType();
  resourceTypeForm: FormGroup;
  resourceTypeName:"";

  constructor(private resourceTypeService: ResourceTypeService, private router: Router, private fb: FormBuilder) { 
    this.resourceTypeForm = this.fb.group({
      resourceTypeName: ['', Validators.required]

    });

  }

  ngOnInit() {
   
  }
  


  addResourceType() {
    if (this.resourceTypeForm.valid) {
      const newResourceType = { ...this.resourceTypeForm.value };

      this.resourceTypeService.addResourceType(newResourceType).subscribe(
        (response) => {
          // Afficher une alerte de succès avec SweetAlert
          Swal.fire({
            icon: 'success',
            title: 'Type de ressource ajouté avec succès!',
            showConfirmButton: false,
            timer: 1500 // Fermer automatiquement l'alerte après 1.5 seconde
          });
          setTimeout(() => {
            this.router.navigate(['/resourceTypesList']);
          }, 1500);
        },
        (error) => {
          console.error('Erreur lors de l\'ajout du type de ressource : ', error);
        }
      );
    }
  }

}
