import { Component, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { Resource } from '../resource-list/resource';
import { ResourceService } from '../resource-list/resource.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-resource',
  templateUrl: './add-resource.component.html',
  styleUrls: ['./add-resource.component.css']
})
export class AddResourceComponent {

  submitted = false;
  constructor(private resourceService : ResourceService , private router : Router , private fb: FormBuilder) { 
    this.resourceForm = this.fb.group({
      resourceName: ['', Validators.required],
      date: [null, Validators.required],
      isAvailable:[false] // Vous pouvez ajouter d'autres validations selon vos besoins
    });

  }
 
  resource: Resource= new Resource();
 resourceForm : FormGroup;
  ngOnInit() {
  }
  // saveProduct() {
  //   this.resourceService.addResource(this.resource)
  //     .subscribe(data => console.log(data), error => console.log(error));
  //   this.resource = new Resource();
  //   //this.gotoProductList();
  // }
  addResource() {
    if (this.resourceForm.valid) {
      const newResource = this.resourceForm.value;

    
      this.resourceService.addResource(newResource).subscribe(
        (response) => {
          // Afficher une alerte de succès avec SweetAlert
          Swal.fire({
            icon: 'success',
            title: 'resource ajouté avec succès!',
            showConfirmButton: false,
            timer: 1500 // Fermer automatiquement l'alerte après 1.5 seconde
          });
        },
        (error) => {
          console.error('Erreur lors de l\'ajout du resource : ', error);
        }
      );
    }
  }





}
