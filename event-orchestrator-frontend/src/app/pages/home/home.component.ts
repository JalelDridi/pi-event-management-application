import {Component, OnInit} from '@angular/core';
import {SearchService} from "../../services/searchservice";
import {EventService} from "../../services/eventservices/eventservice/event.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  content: any[] = [];
  filteredContent: any[] = [];
  keyword: string = '';
  mostAnticipated: any[] = [];
  error: string = '';
  constructor(private searchService: SearchService,
              private Event: EventService,
              private router: Router) { }

  ngOnInit(): void {
    this.loadUpcomingEvents();
  }

  filterContent(): void {
    this.filteredContent = this.content.filter(item => item.name.toLowerCase().includes(this.keyword.toLowerCase()));
  }

  loadUpcomingEvents(): void {
    this.Event.getUpcomingEvents().subscribe({
      next: (events) => {
        // Sort the events by capacity in descending order
        events.sort((a, b) => b.capacity - a.capacity);

        // Take the top 7 events with the most capacity
        this.mostAnticipated = events.slice(0, 7);

        // Set the content to the mostAnticipated events
        this.content = this.mostAnticipated;

        // Filter the content after loading the events
        this.filterContent();
      },
      error: (error) => {
        console.error('Error fetching upcoming events:', error);
        this.error = 'Failed to load upcoming events';
      }
    });
  }



  navigateToEventDetails(eventId: string): void {
    this.router.navigate(['/eventdetails', eventId]);
  }
}
