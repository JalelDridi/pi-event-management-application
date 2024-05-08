import {Component, OnInit} from '@angular/core';
import {EventService} from "../../services/eventservices/eventservice/event.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-all-events',
  templateUrl: './all-events.component.html',
  styleUrls: ['./all-events.component.css']
})
export class AllEventsComponent implements OnInit{


  eventList: any[] = []; // Provide an initial value
  searchTerm: string = '';
  isExpanded: boolean = false;
  upcomingEvents: Event[] = [];
  isLoading = false;
  error: string | null = null;
  showUpcomingOnly = false;
  paginatedAnnonces: any[] = [];

  filteredAnnonces: any[] = [];
  alltypes:any[]=[]
  currentPage: number = 1;
  itemsPerPage: number = 4;
  totalPages: number = 1;
  loading:boolean=false
  type:string=''
  eventId:number
  eventDetails: any;

  constructor ( private Event: EventService ,private router:Router,private route: ActivatedRoute,private sanitizer: DomSanitizer){ }
  ngOnInit(): void {

    this.loadAllEvents();

    this.route.params.subscribe(params => {
      this.eventId = +params['id'];
      this.loadEventDetails(this.eventId);
    });
    this.getalltype();
  }



  loadAllEvents(): void {

    this.Event.GetEventList().subscribe(
      (events) => {
        this.eventList = events;
        
        this.loading=false
        this.filteredAnnonces = events;
        this.showUpcomingOnly = false;
        this.updatePagination();
      },
      (error) => console.error('Error fetching all events:', error)
    );
  }

  loadUpcomingEvents(): void {

    this.Event.getUpcomingEvents().subscribe({
      next: (events) => {
        this.upcomingEvents = events;
        this.isLoading = false;
        this.showUpcomingOnly = true; // Now show only upcoming events
      },
      error: (error) => {
        console.error('Error fetching upcoming events:', error);
        this.error = 'Failed to load upcoming events';
      }
    });
  }

  loadEventDetails(eventId: number): void {
    this.Event.GetEventDetails(eventId).subscribe({
      next: (data) => {
        this.eventDetails = data;
        console.log('Event details fetched:', this.eventDetails);
        this.router.navigate(['eventdetails', eventId]);
      },
      error: (error) => {
        console.error('Error fetching event details:', error);
      }
    });
  }

  updatePagination(): void {
    const totalItems = this.filteredAnnonces.length;
    this.totalPages = Math.ceil(totalItems / this.itemsPerPage);
    this.setPaginatedAnnonces();
  }

  setPaginatedAnnonces(): void {
    const startIdx = (this.currentPage - 1) * this.itemsPerPage;
    const endIdx = startIdx + this.itemsPerPage;
    this.paginatedAnnonces = this.filteredAnnonces.slice(startIdx, endIdx);
  }

  changePage(page: number): void {
    if (page >= 1 && page <= this.totalPages) {
      this.currentPage = page;
      this.setPaginatedAnnonces();
    }
  }


  onSearch(event: Event): void {
    const inputElement = event.target as HTMLInputElement;
    const searchTerm = inputElement?.value?.toLowerCase();

    if (searchTerm) {
      this.filteredAnnonces = this.eventList.filter((annonce) => {
      });
      this.currentPage = 1;
      this.updatePagination();
    } else {
      this.filteredAnnonces = this.eventList;
      this.currentPage = 1;
      this.updatePagination();
    }
  }
  filtertypes(event:any){

    let value =event.target.value
    if(value=="All"){
      this.loadAllEvents()
    }
    else{
      this.geteventbytype(value)}
  }
  geteventbytype(type: string): void {
    this.Event.getEventsByType(type).subscribe((data: any) => {
        this.filteredAnnonces = data;
        this.currentPage = 1; // Reset to first page
        this.updatePagination(); // Update pagination based on the new filtered list
    }, (error) => {
        console.error('No Events found by this type:', error);
        this.filteredAnnonces = []; // Clear the list if no data found
        this.updatePagination(); // Update pagination
    });
}

exportEvents(): void {
  this.Event.exporteventsToExcel().subscribe(
    (data: Blob) => {
      const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      document.body.appendChild(a);
      a.style.display = 'none';
      a.href = url;
      a.download = 'Events.xlsx';
      a.click();
      window.URL.revokeObjectURL(url);
    },
    error => {
      console.error('Une erreur est survenue lors du téléchargement du fichier Excel : ', error);
    }
  );
}



  getalltype(): void {
    this.Event.getAllCategories().subscribe(
      (events) => {
        this.alltypes = events;
        console.log(this.alltypes)
      },
      (error) => console.error('Error fetching all events:', error)
    );
  }

}
