import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {getWebNotifs} from "../../services/notificationservices/fn/controller/get-web-notifs";
import {Message} from "../../services/notificationservices/models/message";



@Component({
  selector: 'app-notification-bar',
  templateUrl: './notification-bar.component.html',
  styleUrls: ['./notification-bar.component.css']
})
export class NotificationBarComponent implements OnInit {
  notifications: Message[] = [];
  unreadNotifications: Message[] = [];

  constructor(private http: HttpClient) {} // Inject HttpClient for API calls

  ngOnInit() {
    const userId = "666";
    getWebNotifs(this.http, "http://localhost:8060", {userId} )
      .subscribe(notifications => {
        console.log('Response data:', notifications);
        if (notifications && notifications.body && Array.isArray(notifications.body)) {
          this.notifications = notifications.body;
          this.unreadNotifications = this.notifications.filter(message => !message.read);
        } else {
          console.error('Invalid notifications data received:', notifications);
        }
      });
  }

  setUnreadNotificationsToRead() {
    for (const notification of this.unreadNotifications) {

    }
  }



}
