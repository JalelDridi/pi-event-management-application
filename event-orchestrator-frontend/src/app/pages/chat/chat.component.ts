import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ChatMessage} from "../../services/adminservices/chatservices/ChatMessage";
import {ChatService} from "../../services/adminservices/chatservices/chat.service";
import Swal from "sweetalert2";
import {Message} from "../../services/notificationservices/models/message";
import {UserService} from "../../userservices/services/user.service";
import {User} from "../../userservices/models/user";
import {
  NotificationControllerService
} from "../../services/notificationservices/services/notification-controller.service";
import {NotificationDto} from "../../services/notificationservices/models/notification-dto";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  messageInput: string = '';
  userId: string = '';
  messageList: any[] = [];
  message: Message;
  user: User;
  notificationDto: NotificationDto; // A data transfer object to send the message

  constructor(private chatService: ChatService,
              private route: ActivatedRoute,
              private notificationService: NotificationControllerService,
              private userService: UserService) {}

  ngOnInit(): void {
    // this.userId = this.route.snapshot.params["userId"];
    this.userId = localStorage.getItem('userId');
    this.getUserById();
    this.chatService.joinRoom("ABC");
    this.listenMessages();
  }

  async sendMessage() {
    const chatMessage = {
      message: this.messageInput,
      user: this.userId
    } as ChatMessage;

    this.analyzeCommentToxicity(this.messageInput); // Analyze toxicity before sending message

  }


  getUserById()  {
    const userId = this.userId;

    this.userService.getUserById({userId}).subscribe((user) => {
      this.user.fullName = user.firstName + " " + user.lastName;
      }
    );
  }


  sendMessageNotification(message: Message) {
    this.notificationDto.content = this.message.content;
    this.notificationDto.userIdFrom = this.message.userId;
    this.notificationDto.userId = this.message.userId;
    this.notificationDto.subject = this.message.subject;
    this.notificationDto.fullName = this.message.subject;
    this.notificationDto.deliveryChannel = "webNotification";
    const body = this.notificationDto;

    this.notificationService.sendMessage({body});
  }




  listenMessages() {
    this.chatService.getMessageSubject().subscribe((messages: any) => {
      this.messageList = messages.map((item: any) => ({
        ...item,
        message_side: item.user === this.userId ? 'sender' : 'receiver'
      }));

      // Call analyzeCommentToxicity for each new message received
      this.messageList.forEach((message: any) => {
        if (message.message_side === 'receiver') {
        }
      });
    });
  }

  analyzeCommentToxicity(commentText: string): void {
    this.chatService.analyzeComment(commentText).subscribe(
      (result: any) => {
        console.log("Toxicity analysis result:", result);

        // Extract the value of summaryScore
        const summaryScoreValue = result?.attributeScores?.TOXICITY?.summaryScore?.value;

        if (summaryScoreValue && summaryScoreValue > 0.1) {
          Swal.fire({
            title: 'Warning',
            text: 'Your message may be toxic. Please be respectful.',
            icon: 'warning'
          });
        } else {
          // If comment is not toxic, send the message
          const chatMessage = {
            message: commentText,
            user: this.userId
          } as ChatMessage;

          this.chatService.sendMessage("ABC", chatMessage);

          // Save the message in the notification database and notify the users later:
          this.message.content = chatMessage.message;
          this.message.userIdFrom = chatMessage.user;
          this.message.messageType = "chatMessage";
          this.message.subject = this.user.fullName;

          this.sendMessageNotification(this.message)

        }
      },
      (error: any) => {
        console.error("Error analyzing comment:", error);
      }
    );
  }
}
