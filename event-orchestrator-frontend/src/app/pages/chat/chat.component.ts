import { Component } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ChatMessage} from "../../services/adminservices/chatservices/ChatMessage";
import {ChatService} from "../../services/adminservices/chatservices/chat.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  messageInput: string = '';
  userId: string = '';
  messageList: any[] = [];

  constructor(private chatService: ChatService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.userId = this.route.snapshot.params["userId"];
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
        }
      },
      (error: any) => {
        console.error("Error analyzing comment:", error);
      }
    );
  }
}
