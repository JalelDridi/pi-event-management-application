import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Notification {
  subject: string; // Use "subject" property from backend
  content: string; // Use "content" property from backend
  isRead: boolean; // Keep as-is
  sentDate?: Date; // Optional property for sent date (if needed)
}

@Component({
  selector: 'app-notification-bar',
  templateUrl: './notification-bar.component.html',
  styleUrls: ['./notification-bar.component.css']
})
export class NotificationBarComponent implements OnInit {
  notifications: Notification[] = [];
  unreadNotifications: Notification[] = [];

  constructor(private http: HttpClient) {} // Inject HttpClient for API calls

  ngOnInit() {
    this.fetchNotifications();
  }

  fetchNotifications() {
    this.http.get<Notification[]>('http://localhost:8060/notification/get-web-notifications/666')
      .subscribe(notifications => {
        this.notifications = notifications;
        this.unreadNotifications = this.notifications.filter(notification => !notification.isRead);
      });
  }
}
