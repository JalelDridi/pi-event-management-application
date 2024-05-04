import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Message} from "../../services/notificationservices/models/message";
import {getAllMessages} from "../../services/notificationservices/fn/notification-controller/get-all-messages";


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
    getAllMessages(this.http, "http://localhost:8060")
      .subscribe( messages => {
        this.messages = messages.body;
        this.unreadMessages = this.messages.filter(message => !message.read)
      }

    )
  }


  setAllRead(userId: string) {

  }



}
