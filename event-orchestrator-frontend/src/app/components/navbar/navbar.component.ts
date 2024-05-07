import { Component, OnInit, ElementRef } from '@angular/core';
import { ROUTES } from '../sidebar/sidebar.component';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import {EventUserDto} from "../../userservices/models/event-user-dto";
import {UserService} from "../../userservices/services/user.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  keyword: string = '';
  user : EventUserDto;
  public focus;
  public listTitles: any[];
  public location: Location;
  constructor(location: Location,
              private element: ElementRef,
              private router: Router,
              private userService: UserService,
              ) {
    this.location = location;
  }


  ngOnInit() {
    // get user details of the logged-in user
    // Retrieve the user ID from local storage or a service after login
    const userId = localStorage.getItem('userId'); // Assuming user ID is stored in local storage
    if (userId) {
      // If user ID is available, fetch user information
      this.userService.getUserById({ userId }).subscribe(
        (user: EventUserDto) => {
          this.user = user; // Set the fetched user information
        },
        (error) => {
          this.user = {
            firstName: "Marshall",
            lastName: "Mathers",
          }
          console.error('Error fetching user information:', error);
        }
      );
    } else {
      console.error('User ID not found');
      this.user = {
        firstName: "Marshall",
        lastName: "Mathers",
      }
    }
    this.listTitles = ROUTES.filter(listTitle => listTitle);

  }
  getTitle(){
    var titlee = this.location.prepareExternalUrl(this.location.path());
    if(titlee.charAt(0) === '#'){
        titlee = titlee.slice( 1 );
    }

    for(var item = 0; item < this.listTitles.length; item++){
        if(this.listTitles[item].path === titlee){
            return this.listTitles[item].title;
        }
    }
    return 'Dashboard';
  }

  logout(): void {
    // Remove userId and token from cookies
    localStorage.removeItem('userId');
    localStorage.removeItem('token');

    // Redirect to login page
    this.router.navigate(['/login']);
  }

  ////// Event search
  filterEvent() {
    const filter = (document.getElementById('searchInput') as HTMLInputElement).value.toUpperCase();
    const events = document.querySelectorAll('.carousel-item');

    events.forEach(event => {
      const title = (event.querySelector('.carousel-item__details--title') as HTMLElement).innerText.toUpperCase();
      const subtitle = (event.querySelector('.carousel-item__details--subtitle') as HTMLElement).innerText.toUpperCase();
      const combinedText = title + subtitle;

      if (combinedText.includes(filter)) {
        (event as HTMLElement).style.display = ''; // Show the event
      } else {
        (event as HTMLElement).style.display = 'none'; // Hide the event
      }
    });
  }


}
