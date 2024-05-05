import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {getWebNotifs} from "../../services/notificationservices/fn/controller/get-web-notifs";
import {Message} from "../../services/notificationservices/models/message";
import {
  setUserNotificationsAsRead, SetUserNotificationsAsRead$Params
} from "../../services/notificationservices/fn/controller/set-user-notifications-as-read";
import {Router} from "@angular/router";



@Component({
  selector: 'app-notification-bar',
  templateUrl: './notification-bar.component.html',
  styleUrls: ['./notification-bar.component.css']
})
export class NotificationBarComponent implements OnInit {
  // Notifications are of type message because we need their content.
  notifications: Message[] = [];
  // Unread notifications array, So tht we want count them
  unreadNotifications: Message[] = [];
  // So that we can set an array of notification as read instantly
  unreadMessageIds: number[] = [];

  // Inject HttpClient for API calls AND a Router that will be used to open the notification list popup
  constructor(private http: HttpClient, private router: Router) {}

  // When the component is initialized, we'll get the web notifications of a specific user from the database
  ngOnInit() {
    this.getWebNotifications();
  }


  // A method that gets web notification
  getWebNotifications() {
    const userId = "666";
    getWebNotifs(this.http, "http://localhost:8060", {userId} )
      .subscribe(notifications => {
        console.log('Response data:', notifications);
        if (notifications && notifications.body && Array.isArray(notifications.body)) {
          this.notifications = [];
          this.unreadNotifications = [];
          this.unreadMessageIds = [];
          this.notifications = notifications.body.filter(message => message.messageType == "webNotification" || message.messageType == null)
            .sort((a, b) => new Date(b.sentDate).getTime() - new Date(a.sentDate).getTime());
          this.unreadNotifications = this.notifications.filter(message => !message.read);
          // Extract message IDs from unread notifications
          this.unreadMessageIds = this.unreadNotifications.map(message => message.messageId);

        } else {
          console.error('Invalid notifications data received:', notifications);
        }
      });
  }

  // This is triggered when clicking on the notification icon so the notifications will be set as read.
  setUnreadNotificationsToRead() {
    console.log("WORKS");

    // Prepare parameters for setUserNotificationsAsRead
    const params: SetUserNotificationsAsRead$Params = {
      body: this.unreadMessageIds
    };

    // Call the API to mark notifications as read
    setUserNotificationsAsRead(this.http, "http://localhost:8060", params).subscribe(() => {
      // Refresh notifications after marking them as read
      this.getWebNotifications();
    });
  }


  // Format the date so it can be displayed in a readable way
  formatNotificationDate(dateString: string): string {
    const date = new Date(dateString);
    const now = new Date();

    // Check if the date is today
    if (date.toDateString() === now.toDateString()) {
      const hours = date.getHours().toString().padStart(2, '0');
      const minutes = date.getMinutes().toString().padStart(2, '0');
      return `${hours}:${minutes}`;
    } else {
      const day = date.getDate().toString().padStart(2, '0');
      const month = (date.getMonth() + 1).toString().padStart(2, '0'); // Months are zero-based
      const year = date.getFullYear().toString();
      const hours = date.getHours().toString().padStart(2, '0');
      const minutes = date.getMinutes().toString().padStart(2, '0');
      return `${hours}:${minutes} ${day}-${month}-${year}`;
    }
  }


  // navigate
  navigateToNotificationPopup() {
    this.router.navigate(['/notification-popup']);
  }




}
