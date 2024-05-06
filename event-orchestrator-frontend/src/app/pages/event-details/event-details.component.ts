import {Component, OnInit} from '@angular/core';
import Swal from "sweetalert2";
import {EventService} from "../../services/eventservices/eventservice/event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {QrCodeService} from "../../services/eventservices/qr-code.service";
import {Eventattribut} from "../../services/eventservices/models/event.model";

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.scss']
})
export class EventDetailsComponent implements OnInit{


  eventId: number;
  eventDetails: any;
  UserId: string = localStorage.getItem("userId");
  userevents: any[] = [];
  isUserEvent: boolean = false;
  mytext :string
  event: Eventattribut = {
    name: '',
    description: '',
    startDate: null,
    endDate: null,
    eventType: '',
    Club: ''
  };
  Participation :any;


  constructor(private eventservice: EventService, private route: ActivatedRoute, private router: Router, private dialog: MatDialog ,private qrCodeService: QrCodeService ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.eventId = +params['id'];
      this.loadEventDetails(this.eventId);
      this.loadUserEvents();
    });
  }

  loadEventDetails(eventId: number): void {
    this.eventservice.GetEventDetails(eventId).subscribe({
      next: (data) => {
        this.eventDetails = data;
        console.log('Event details loaded:', this.eventDetails);
        this.updateEditDeletePermissions();
        this.router.navigate(['EditEvent', eventId]);

      },
      error: (error) => console.error('Error fetching event details:', error)
    });
  }

  ReformDate(date: string): string {
    const readableDate = new Date(date);
    return readableDate.toLocaleString("en-US", {
      year: "numeric",
      month: "long",
      day: "numeric",
      hour: "numeric",
      minute: "2-digit",
      hour12: true,
    });
  }
  editEvent(eventId: number): void {
    this.router.navigate(['EditEvent', eventId]);
  }
  DeleteEvent(eventId : number ): void {
    Swal.fire({
      title: "Êtes-vous sûr(e) ?",
      text: "Vous ne pourrez pas revenir en arrière !",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Oui, supprimer !"
    }).then((result) => {
      if (result.isConfirmed) {
        this.eventservice.DeleteEvent(this.eventId).subscribe({
          next: (response) => {
            console.log("Event deleted successfully", response);
            Swal.fire("Supprimé !", "Votre Event a été supprimé.", "success");
            this.router.navigateByUrl('/EventList');
          },
          error: (error) => {
            console.error("Failed to delete event", error);
            Swal.fire("Échec", "La suppression de l'événement a échoué.", "error");
          }
        });
      }
    });
  }

  loadUserEvents(): void {
    this.eventservice.GetUserevent(this.UserId).subscribe(
      data => {
        this.userevents = data;
        this.updateEditDeletePermissions();
        console.log(this.userevents)
      },
      error => console.error('Error fetching user events:', error)
    );
  }

  updateEditDeletePermissions() {
    if (this.eventDetails && this.userevents.length) {
      this.isUserEvent = this.userevents.some(event => event.eventId === this.eventDetails.eventId);
    }
  }

  qrdata :string='hello' ;

  updateServiceData() {
    const newData = 'Hello, new data!';  // Example new data
    this.qrCodeService.updateQrData(newData);
  }


  openQRDialog(qrdata  :string): void{
    this.updateServiceData()

  }
  Addparticipation() {
    this.eventservice.AddParticipation(this.eventId,this.UserId).subscribe(
      (data: any) => {
        this.Participation = data;
        console.log('Event added successfully:', data);
      },
      error => {
        console.error('An error occurred while adding the event:', error);
      }
    );
  }
}
