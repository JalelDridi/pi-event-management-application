import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Reclamation } from '../../ReviewModels/Reclamation.Model';
import { TypeReclamation } from '../../ReviewModels/type-reclamation.enum';
import { ReclamationService } from '../../reviewservices/reclamation.service';
import { BadWordsFilterService } from '../../reviewservices/badwordsfilter.service';
import Swal from 'sweetalert2';
import {SpringMailControllerService} from "../../services/notificationservices/services/spring-mail-controller.service";
import {UserService} from "../../userservices/services/user.service";
import {
  NotificationControllerService
} from "../../services/notificationservices/services/notification-controller.service";

@Component({
  selector: 'app-add-reclamation',
  templateUrl: './add-reclamation.component.html',
  styleUrls: ['./add-reclamation.component.css']
})
export class AddReclamationsComponent {
  reclamationForm: FormGroup;
  typesReclamation = Object.values(TypeReclamation);
  events: any[] = [];
  resources: any[] = [];
  userId = localStorage.getItem('userId');
  date = new Date();

  constructor(
    private formBuilder: FormBuilder,
    private reclamationService: ReclamationService,
    private filterService: BadWordsFilterService,
    private http: HttpClient,
    private userService: UserService,
    private emailService: SpringMailControllerService,
    private notificationService: NotificationControllerService
  ) {
    this.reclamationForm = this.formBuilder.group({
      eventId: [''],
      typeRec: ['', Validators.required],
      content: ['', Validators.required],
      resourceId:['']
    });

    this.reclamationForm.get('typeRec').valueChanges.subscribe(value => {
      if (value === 'EVENT') {
        this.fetchEvents();
      } else if (value === 'RESOURCE') {
        this.fetchResources();
      }
    });
  }

  fetchEvents() {
    this.http.get<any[]>(`http://localhost:8089/Event/getall`).subscribe(events => {
      this.events = events.map(event => ({ id: event.eventId, name: event.name }));
    });
  }

  fetchResources() {
    this.http.get<any[]>(`http://localhost:8080/api/resources/all-resources`).subscribe(resources => {
      this.resources = resources.map(resource => ({ id: resource.resourceID, name: resource.resourceName }));
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
        eventId: this.reclamationForm.value.eventId,
        userId: this.userId,
        typeRec: this.reclamationForm.value.typeRec,
        content: content,
        reponse: null,
        dateReclamation: this.date
      };

      this.showConfirmationModal(reclamation);
    }
  }

  resetForm() {
    this.reclamationForm.reset();
    this.reclamationForm.markAsPristine();
  }

  showBadWordsAlert() {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Reclamation contains bad words. Please remove them.'
    });
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
            this.notifyUser(reclamation.content);
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
    Swal.fire({
      icon: 'success',
      title: 'Success',
      text: 'Reclamation added successfully!'
    });
  }

  showErrorAlert() {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Error adding reclamation. Please try again later.'
    });
  }





  notifyUser(content: string) {
    var email;
    this.userService.getUserById({ userId: this.userId }).subscribe(user => {
      email = user.email;
    });

    this.emailService.sendNotificationHtml({
      body: {
        userId: this.userId,
        email: email,
        subject: "Your feedback has been sent successfully!",
        content: content
      }
    }).subscribe(() => {
      console.log("Reclamation sent successfully!");
    });

    this.notificationService.sendWebNotification({
      body: {
        userId: this.userId,
        email: email,
        subject: "Your feedback has been sent successfully!",
        content: content
      }
    }).subscribe();
  }


  showNetworkErrorToast(title: string, message: string) {
    Swal.fire({
      icon: 'error',
      title: title,
      text: message
    });
  }
}
