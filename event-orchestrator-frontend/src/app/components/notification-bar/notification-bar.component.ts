import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {UserService} from "../../userservices/services";
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
    getWebNotifs(this.http, getWebNotifs.PATH, {userId} )
      .subscribe(notifications => {
        this.notifications = notifications.body!;
        this.unreadNotifications = this.notifications.filter(notification => !notification.read);
      });
  }

}
