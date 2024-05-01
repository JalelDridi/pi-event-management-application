// Angular core imports
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
 
// Model and service imports
import { Reclamation } from '../../ReviewModels/Reclamation.Model'
import { TypeReclamation } from '../../ReviewModels/type-reclamation.enum';
import { ReclamationService } from '../../reviewservices/reclamation.service';
import { BadWordsFilterService } from '../../reviewservices/badwordsfilter.service';
import Swal from 'sweetalert2'; // SweetAlert2 for confirmation dialogs
 
@Component({
  selector: 'app-add-reclamation',
  templateUrl: './add-reclamation.component.html',
  styleUrls: ['./add-reclamation.component.css']
})
export class AddReclamationsComponent {
  reclamationForm: FormGroup;
  typesReclamation = Object.values(TypeReclamation);
 
  constructor(
    private formBuilder: FormBuilder,
    private reclamationService: ReclamationService,
    private filterService: BadWordsFilterService
    // ToastrService is commented out intentionally to remove it
  ) {
    this.reclamationForm = this.formBuilder.group({
      eventId: ['', Validators.required],
      typeRec: ['', Validators.required],
      content: ['', Validators.required]
    });
  }
 
  addReclamation() {
    if (this.reclamationForm.valid) {
      const content = this.reclamationForm.value.content;
      if (this.filterService.containsBadWords(content)) {
        this.showBadWordsAlert();
        return;
      }
 
      const reclamation: Reclamation = {
        IdRec: 1,
        eventId: this.reclamationForm.value.eventId,
        userId: '2',
        typeRec: this.reclamationForm.value.typeRec,
        content: content,
        dateReclamation: new Date()
      };
 
      this.showConfirmationModal(reclamation);
    }
  }
 
  resetForm() {
    this.reclamationForm.reset();
    this.reclamationForm.markAsPristine();
  }
 
  showBadWordsAlert() {
    // ToastrService usage is commented out intentionally
    // this.toastr.error('Reclamation contains bad words. Please remove them.', 'Warning', {
    //   positionClass: 'toast-bottom-center'
    // });
    console.log('Reclamation contains bad words. Please remove them.');
  }
 
  showConfirmationModal(reclamation: Reclamation) {
    const message = `<div class="message">
<p>Event ID: ${reclamation.eventId}</p>
<p>Type: ${reclamation.typeRec}</p>
<p>Content: ${reclamation.content}</p>
</div>`;
    Swal.fire({
      html: `Are you sure to add this reclamation?<br><br>${message}`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#1b5f01',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Add Reclamation',
      cancelButtonText: 'No, cancel!',
      reverseButtons: true,
    }).then((result) => {
      if (result.isConfirmed) {
        this.reclamationService.addReclamation(reclamation).subscribe(
          response => {
            console.log('Reclamation added successfully:', response);
            this.showSuccessAlert();
            this.resetForm();
          },
          (error: HttpErrorResponse) => {
            console.error('Error adding reclamation:', error);
            if (error.error instanceof ErrorEvent) {
              this.showNetworkErrorToast('An error occurred:', error.error.message);
            } else {
              this.showNetworkErrorToast(`Server returned code ${error.status}`, error.message);
            }
          }
        );
      }
    });
  }
 
  showSuccessAlert() {
    // ToastrService usage is commented out intentionally
    // this.toastr.success('Reclamation added successfully!', '', {
    //   positionClass: 'toast-bottom-center',
    //   timeOut: 3000
    // });
    console.log('Reclamation added successfully!');
  }
 
  showErrorAlert() {
    // ToastrService usage is commented out intentionally
    // this.toastr.error('Error adding reclamation. Please try again later.', 'Error', {
    //   positionClass: 'toast-bottom-center',
    //   timeOut: 3000
    // });
    console.log('Error adding reclamation. Please try again later.');
  }
 
  showNetworkErrorToast(title: string, message: string) {
    // ToastrService usage is commented out intentionally
    // this.toastr.error(`${title} ${message}`, 'Network Error', {
    //   positionClass: 'toast-bottom-center',
    //   timeOut: 3000
    // });
    console.log(`${title} ${message}`);
  }
}

