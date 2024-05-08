import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
  { path: '/home', title: 'Home',  icon: 'ni-shop text-primary', class: '' },
  { path: '/login', title: 'Login',  icon:'ni-key-25 text-info', class: '' },
  { path: '/register', title: 'Register',  icon:'ni-circle-08 text-pink', class: '' },
  { path: '/user-profile', title: 'User profile',  icon:'ni-single-02 text-yellow', class: '' },
  { path: '/admindashboard', title: 'Admin Dashboard',  icon: 'ni-settings-gear-65 text-primary', class: '' },
  { path: '/userlist', title: 'Accept New Users',  icon:'ni-badge text-primary', class: '' },
  { path: '/userlistFinal', title: 'Users List',  icon:'ni-bullet-list-67 text-primary', class: '' },
  { path: '/allevents', title: 'All events',  icon: 'ni-calendar-grid-58 text-primary', class: '' },
  { path: '/myevents', title: 'My events',  icon: 'ni-album-2 text-primary', class: '' },
  { path: '/myparticipations', title: 'My participations',  icon: 'ni-check-bold text-primary', class: '' },
  { path: '/requestevent', title: 'Create event request',  icon: 'ni-fat-add text-primary', class: '' },
  { path: '/admineventlist', title: 'Event Requests',  icon:'ni-folder-17 text-primary', class: '' },
  { path: '/resources', title: 'All resources',  icon: 'ni-books text-primary', class: '' },
  { path: '/RessourceList', title: 'Resources List',  icon:'ni-collection text-primary', class: '' },
  { path: '/submitfeedback', title: 'Submit a feedback',  icon: 'ni-chat-round text-primary', class: '' },

  { path: '/resources', title: 'All resources',  icon: 'ni-collection text-primary', class: '' },
  { path: '/userfeedbacks', title: 'User feedbacks',  icon: 'ni-collection text-primary', class: '' },
  { path: '/dashboard', title: 'Dashboard',  icon: 'ni-chart-bar-32 text-primary', class: '' },
  { path: '/icons', title: 'Icons',  icon:'ni-diamond text-blue', class: '' },
  { path: '/maps', title: 'Maps',  icon:'ni-map-big text-orange', class: '' },
  { path: '/user-profile', title: 'User profile',  icon:'ni-single-02 text-yellow', class: '' },
  { path: '/tables', title: 'Tables',  icon:'ni-align-left-2 text-red', class: '' },
  { path: '/login', title: 'Login',  icon:'ni-user-run text-info', class: '' },
  { path: '/register', title: 'Register',  icon:'ni-circle-08 text-pink', class: '' },
  { path: '/reclamationtest', title: 'Reclamation',  icon:'ni-notification-70 text-black', class: '' },
  { path: '/reviewtest', title: 'Reviews',  icon:'ni-spaceship text-pink', class: '' },
  { path: '/userlist', title: 'accept new Users',  icon:'ni-single-copy-04 text-primary', class: '' },
  { path: '/userlistFinal', title: 'Users List',  icon:'ni-single-copy-04 text-primary', class: '' },
  { path: '/RessourceList', title: 'Ressources List',  icon:'ni-single-copy-04 text-primary', class: '' },
  { path: '/reviewstatistics', title: 'Review Statistics',  icon:'ni-single-copy-04 text-primary', class: '' },
  { path: '/reclamationstatistics', title: 'Reclamation Statistics',  icon:'ni-single-copy-04 text-primary', class: '' },
  { path: '/admineventlist', title: 'Event Requests',  icon:'ni-single-copy-04 text-primary', class: '' },
  { path: '/userfeedbacks', title: 'User feedbacks',  icon: 'ni-satisfied text-primary', class: '' },
  { path: '/reclamationtest', title: 'Reclamation',  icon:'ni-support-16 text-black', class: '' },


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
