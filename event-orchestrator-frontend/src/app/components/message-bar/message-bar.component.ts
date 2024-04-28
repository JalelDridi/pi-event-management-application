import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

interface Message {
  subject: string;
  content: string;
  isRead: boolean;
  sentDate?: Date;
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
    this.http.get<Message[]>('http://localhost:8060/notification/get-web-notifications/666')
      .subscribe(messages => {
        this.messages = messages;
        this.unreadMessages = this.messages.filter(message => !message.isRead);
      });
  }
}
