import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

interface Message {
  messageId: number
  subject: string;
  content: string;
  isRead: boolean;
  sentDate?: Date;
  userId: string;
  userIdFrom: string;
}

@Component({
  selector: 'app-message-bar',
  templateUrl: './message-bar.component.html',
  styleUrls: ['./message-bar.component.css']
})
export class MessageBarComponent {

  messages: Message[] = [];
  unreadMessages: Message[] = [];

  constructor(private http: HttpClient) {} // Inject HttpClient for API calls

  ngOnInit() {
    this.fetchMessages();
  }

  fetchMessages() {
    this.http.get<Message[]>('http://localhost:8060/notification/get-all-msgs')
      .subscribe(messages => {
        if (messages && messages.length > 0) { // Check if messages are fetched and not empty
          this.messages = messages;
          this.unreadMessages = this.messages.filter(message => !message.isRead);
          console.log(this.messages[0].messageId); // Access messageId only if messages exist
        }
      });
  }


  setAllRead(userId: string) {
    const payload = { userId }; // Create payload with userId

    this.http.post<number>('http://localhost:8060/notification/set-messages-read', payload)
      .subscribe(response => {
        if (response) { // Handle successful response (optional)
          // Update local message state (optional)
          this.messages = this.messages.map(message => {
            if (message.userId === userId) {
              return { ...message, isRead: true }; // Update message object to read
            }
            return message; // Keep message unchanged
          });
          this.unreadMessages = this.messages.filter(message => !message.isRead);
        } else {
          console.error('Failed to mark messages as read'); // Handle error (optional)
        }
      });
  }



}
