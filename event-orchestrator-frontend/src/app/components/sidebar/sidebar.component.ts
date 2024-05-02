import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/home', title: 'Home',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/admindashboard', title: 'Admin Dashboard',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/allevents', title: 'All events',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/myparticipations', title: 'My participations',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/myevents', title: 'My events',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/requestevent', title: 'Create event request',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/submitfeedback', title: 'Submit a feedback',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/resources', title: 'All resources',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/dashboard', title: 'Dashboard',  icon: 'ni-tv-2 text-primary', class: '' },
    { path: '/icons', title: 'Icons',  icon:'ni-planet text-blue', class: '' },
    { path: '/maps', title: 'Maps',  icon:'ni-pin-3 text-orange', class: '' },
    { path: '/user-profile', title: 'User profile',  icon:'ni-single-02 text-yellow', class: '' },
    { path: '/tables', title: 'Tables',  icon:'ni-bullet-list-67 text-red', class: '' },
    { path: '/login', title: 'Login',  icon:'ni-key-25 text-info', class: '' },
    { path: '/register', title: 'Register',  icon:'ni-circle-08 text-pink', class: '' },
    { path: '/reclamationtest', title: 'Reclamation',  icon:'ni-circle-08 text-black', class: '' },
    { path: '/reviewtest', title: 'Reclamation',  icon:'ni-circle-08 text-pink', class: '' }
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  public menuItems: any[];
  public isCollapsed = true;

  constructor(private router: Router) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.router.events.subscribe((event) => {
      this.isCollapsed = true;
   });
  }
}
