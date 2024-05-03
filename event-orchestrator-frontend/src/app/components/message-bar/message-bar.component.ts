import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {getAllMessages} from "../../services/notificationservices/fn/controller/get-all-messages";
import {Message} from "../../services/notificationservices/models/message";


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
