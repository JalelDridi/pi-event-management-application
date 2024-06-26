import {Component, OnInit} from '@angular/core';
import Swal from "sweetalert2";
import {EventService} from "../../services/eventservices/eventservice/event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {Eventattribut} from "../../services/eventservices/models/event.model";
import {SpringMailControllerService} from "../../services/notificationservices/services/spring-mail-controller.service";
import {UserService} from "../../userservices/services/user.service";
import {
  NotificationControllerService
} from "../../services/notificationservices/services/notification-controller.service";

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
    Club: '',
    image: null,
    image1: null
  };
  Participation :any;
userId:string='.'//this.userId = localStorage.getItem('userId');

  constructor(private eventservice: EventService, private route: ActivatedRoute, private router: Router, private dialog: MatDialog, private notificationService: NotificationControllerService, private userService: UserService ) { }

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
        // this.router.navigate(['EditEvent', eventId]);

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




  Addparticipation() {
    this.eventservice.AddParticipation(this.eventId, this.UserId).subscribe(
      (data: any) => {

        console.log(this.userId);
        this.notificationService.sendWebNotification({
          body: {
            userId: this.userId,
            email: email,
            subject: "Participation confirmation message",
            content: "Thank you for your participation to the event " + this.eventDetails.name + ". Don't miss out !"
          }
        }).subscribe(() => {
          console.log("Notification sent successfully!");
      }
        );


        var email;
        this.userService.getUserById({userId : this.userId }).subscribe(user => {
          email = user.email;

        });

        this.notificationService.confirmParticipation({
          userId: this.userId,
          eventId: this.eventId,
          Authorization: localStorage.getItem("token")
        });
        this.Participation = data;
        console.log('Event added successfully:', data);
      },
      error => {
        console.error('An error occurred while adding the event:', error);
      }
    );

  }










}
