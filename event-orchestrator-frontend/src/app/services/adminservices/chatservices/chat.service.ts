import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ChatMessage } from './ChatMessage';
import { HttpClient } from '@angular/common/http';
import * as SockJS from 'sockjs-client';

import { Stomp } from '@stomp/stompjs';


@Injectable({
  providedIn: 'root'
})
export class ChatService {
 private baseUrl:"http://localhost:8092/"

  private stompClient: any
  private messageSubject: BehaviorSubject<ChatMessage[]> = new BehaviorSubject<ChatMessage[]>([]);

  constructor(private http:HttpClient) { 
    this.initConnenctionSocket();
  }

  initConnenctionSocket() {
    const url = '//localhost:8092/chat-socket'
    const socket = new SockJS(url);
    this.stompClient = Stomp.over(socket)
  }

  joinRoom(roomId: string) {
    this.stompClient.connect({}, ()=>{
      this.stompClient.subscribe(`/topic/${roomId}`, (messages: any) => {
        const messageContent = JSON.parse(messages.body);
        const currentMessage = this.messageSubject.getValue();
        currentMessage.push(messageContent);

        this.messageSubject.next(currentMessage);

      })
    })
  }

  sendMessage(roomId: string, chatMessage: ChatMessage) {
    this.stompClient.send(`/app/chat/${roomId}`, {}, JSON.stringify(chatMessage));
  }

  getMessageSubject(){
    return this.messageSubject.asObservable();
  }

  analyzeComment(commentText: string): Observable<any> {
    return this.http.post<any>('http://localhost:8092/analyze-comment', { commentText });
  }
  
}
