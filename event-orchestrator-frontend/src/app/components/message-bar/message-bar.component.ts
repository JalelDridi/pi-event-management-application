import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Message} from "../../services/notificationservices/models/message";
import {Subject} from "rxjs";
import {Router} from "@angular/router";
import {
  NotificationControllerService
} from "../../services/notificationservices/services/notification-controller.service";
import {
  SetUserNotificationsAsRead$Params
} from "../../services/notificationservices/fn/notification-controller/set-user-notifications-as-read";


@Component({
  selector: 'app-message-bar',
  templateUrl: './message-bar.component.html',
  styleUrls: ['./message-bar.component.css']
})
export class MessageBarComponent implements OnInit{
  // Subject to emit signal when notifications change
  private unreadMessagesChanged = new Subject<void>();

  userId : string;
  // Notifications are of type message because we need their content.
  messages: Message[] = [];
  // Unread notifications array, So tht we want count them
  unreadMessages: Message[] = [];
  // So that we can set an array of notification as read instantly
  unreadMessageIds: number[] = [];
  // Unread messages count!
  unreadMessagesCount: number = 0;
  // Inject HttpClient for API calls AND a Router that will be used to open the notification list popup
  constructor(private http: HttpClient,
              private router: Router,
              private notificationService: NotificationControllerService) {}




  // When the component is initialized, we'll get the chat messages of a specific user from the database
  ngOnInit() {
    this.userId = localStorage.getItem('userId') || '666'; // if the is no user credentials stored user mock userId (for testing purposes)

    // Subscribe to notificationsChanged subject to call getWebNotifications() when a change occurs
    this.getChatMessages();

    this.unreadMessagesChanged.subscribe(() => {
      this.countUnreadMessages();

      // Initial Api call to fetch count
      this.countUnreadMessages();
    });
  }


  // When the notification component is destroyed (in the case of logout)
  ngOnDestroy() {
    // Clean up subscription
    this.unreadMessagesChanged.unsubscribe();
  }



  // A method that gets web notification
  getChatMessages() {
    this.userId = localStorage.getItem('userId') || '666';
    this.notificationService.getChatMessages()
      .subscribe(
        messages => {
          console.log('Response data:', messages);
          if (messages && Array.isArray(messages)) {
            this.messages = [];
            this.unreadMessages = [];
            this.unreadMessageIds = [];
            this.messages = messages.filter(message => message.messageType == "chatMessage" && message.userIdFrom !== this.userId)
              .sort((a, b) => new Date(b.sentDate).getTime() - new Date(a.sentDate).getTime());
            this.unreadMessages = this.messages.filter(message => !message.read);
            // Extract message IDs from unread notifications
            this.unreadMessageIds = this.unreadMessages.map(message => message.messageId);
            this.triggerUnreadMessagesUpdate();
          } else {
            console.error('Invalid notifications data received:', messages);
          }
        }
      );
  }

  // This is triggered when clicking on the notification icon so the notifications will be set as read.
  countUnreadMessages(): void {
    console.log("WORKS");
    // Call the API to count unread messages
    this.notificationService.countUnreadMessages().subscribe(
      (count: number) => {
        // Update the count value
        this.unreadMessagesCount = count;
      },
      (error) => {
        console.error('Error counting unread messages:', error);
      }
    );
  }

  // THis method triggers unread messages count update
  triggerUnreadMessagesUpdate(): void {
    this.unreadMessagesChanged.next();
  }

  // This is triggered when clicking on the notification icon so the notifications will be set as read.
  setMessagesAsRead() {
    // Prepare parameters for setUserNotificationsAsRead
    const params: SetUserNotificationsAsRead$Params = {
      body: this.unreadMessageIds
    };
    // Call the API to mark messages as read
    this.notificationService.setUserNotificationsAsRead(params).subscribe(() => {
      // Refresh notifications after marking them as read
      this.getChatMessages();
    });
  }


  // Format the date so it can be displayed in a readable way
  formatMessageDate(dateString: string): string {
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


  // navigate to the chat room
  navigateToChatRoom() {
    this.router.navigate(['/ChatRoom']);
  }

}
