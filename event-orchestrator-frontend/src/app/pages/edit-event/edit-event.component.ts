import {Component, OnInit} from '@angular/core';
import {EventService} from "../../services/eventservices/eventservice/event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Eventattribut} from "../../services/eventservices/models/event.model";

interface EventType {
  value: string;
  viewValue: string;
}


@Component({
  selector: 'app-edit-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['./edit-event.component.scss']
})
export class EditEventComponent implements OnInit{
  translatedEvents: any[] = [];

  eventId: number; // This will hold the ID of the event to edit
  event: Eventattribut = { // Initialize event object
    name: '',
    description: '',
    startDate: null,
    endDate: null,
    eventType: '',
    Club: '',
    image: null,
    image1: null
  };
  eventDetails:[]=[]

  constructor(
    private eventService: EventService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.eventId = +params['id'];
      this.loadEventDetails(this.eventId);

    });


  }

  EventType: EventType[] = [
    {value: 'Sportif-0', viewValue: 'Sportif'},
    {value: 'Culturelle-1', viewValue: 'Culturelle'},
    {value: 'sociale-2', viewValue: 'sociale'},
  ];

  ResourceType: EventType[] = [
    {value: 'Salle-0', viewValue: 'salle'},
    {value: 'Projecteur-1', viewValue: 'projecteur'},
    {value: 'emphie-2', viewValue: 'emphie'},
  ];
  Resource: EventType[] = [
    {value: '1-0', viewValue: 'salle'},
    {value: '2-1', viewValue: 'projecteur'},
    {value: '3-2', viewValue: 'emphie'},
  ];


  

  loadEventDetails(eventId: number): void {
    this.eventService.GetEventDetails(eventId).subscribe({
      next: (data) => {
        this.event = data;
        console.log('Event details loaded:', this.eventDetails);
      },
      error: (error) => console.error('Error fetching event details:', error)
    });
  }
  updateEvent(): void {
    // Assuming 'eventId' is a number and 'this.event' is of type 'Event'
    this.eventService.editEvent(this.eventId, this.event).subscribe(
      (response) => {
        console.log('Event updated successfully:', response);
        this.router.navigate(['/EventList']); // Navigate to the event list or appropriate page
      },
      error => {
        console.error('Error updating event:', error);
      }
    );
  };


  deleteEvent(): void {
    this.eventService.DeleteEvent(this.eventId).subscribe(
      (response) => {
        console.log('Event deleted successfully:', response);
        // Navigate back to event list or dashboard
        this.router.navigate(['/events']);
      },
      error => {
        console.error('Error deleting event:', error);
      }
    );
  }

}
