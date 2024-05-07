import { Component } from '@angular/core';
import {AdminEventListService} from "../../../services/adminservices/admin-event-list.service";

@Component({
  selector: 'app-admin-event-list',
  templateUrl: './admin-event-list.component.html',
  styleUrls: ['./admin-event-list.component.css']
})
export class AdminEventListComponent {


  EventList: any[] = [];

  constructor(private event:AdminEventListService) { }

  ngOnInit(): void {
    this.event.getEventList().subscribe((events: any[]) => {
      this.EventList = events;
    });
  }

  deleteEvent(id: number): void {
    console.log(id);
    this.event.deleteEvent(id).subscribe(
      response => {
        // Handle success response
        console.log('User deleted successfully:', response);

      },
      error => {
        // Handle error response
        console.error('Error deleting user:', error);
      }
    );
  }

  AcceptEvent(id: number): void {
    console.log(id);
    this.event.AcceptEvent(id).subscribe(
      response => {
        // Handle success response
        console.log('event added successfully:', response);

      },
      error => {
        // Handle error response
        console.error('Error adding event:', error);
      }
    );
  }
}
