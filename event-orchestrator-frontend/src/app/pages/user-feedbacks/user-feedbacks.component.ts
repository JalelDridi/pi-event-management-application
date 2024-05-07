import {Component, OnInit} from '@angular/core';
import {ReclamationService} from "../../reviewservices/reclamation.service";
import {Reclamation} from "../../ReviewModels/Reclamation.Model";
import {EventUserDto} from "../../userservices/models/event-user-dto";
import {Router} from "@angular/router";
import {UserService} from "../../userservices/services/user.service";
import {any} from "codelyzer/util/function";
import {NotificationDto} from "../../services/notificationservices/models/notification-dto";
import {SpringMailControllerService} from "../../services/notificationservices/services/spring-mail-controller.service";
import {HttpErrorResponse} from "@angular/common/http";
import {
  NotificationControllerService
} from "../../services/notificationservices/services/notification-controller.service";

@Component({
  selector: 'app-user-feedbacks',
  templateUrl: './user-feedbacks.component.html',
  styleUrls: ['./user-feedbacks.component.css']
})
export class UserFeedbacksComponent implements OnInit {

  user: EventUserDto;
  reclamationList: Reclamation[];
  emails: string[] = [];
  usernames: string[] = [];
  notificationDto: NotificationDto;

  constructor(private userService: UserService,
              private reclamationService: ReclamationService,
              private emailService: SpringMailControllerService,
              private notificationService: NotificationControllerService,) { }

  ngOnInit(): void {
    const userId = localStorage.getItem('userId'); // Assuming user ID is stored in local storage
    if (userId) {
      this.userService.getUserById({ userId }).subscribe(
        (user: EventUserDto) => {
          this.user = user;
        },
        (error) => {
          console.error('Error fetching user information:', error);
        }
      );
    } else {
      console.error('User ID not found');
    }

    this.reclamationService.getAllReclamations().subscribe(reclamations => {
      this.reclamationList = reclamations;
      // Now that we have the reclamations, we can iterate over them
      this.reclamationList.forEach(reclamation => {
        this.userService.getUserById({ userId: reclamation.userId }).subscribe(user => {
          this.emails.push(user.email);
          this.usernames.push(user.firstName + " " + user.lastName);
        });
      });
    });
  }



  respondToReclamation(index: number, response: string): void {
    // Use the 'response' parameter to send an email or perform other actions
    console.log(response); // For demonstration purposes

    // ... rest of your code to handle the response
    const header = `Hello, ${this.usernames[index]} Thank you for your feedback, here is our response: \n`;


    this.notificationService.sendWebNotification({
      body: {
        userId: this.reclamationList[index].userId,
        email: this.emails[index],
        subject: "Your feedback has been treated successfully!",
        content: response
      }
    }).subscribe();


    this.emailService.sendNotificationHtml({
      body: {
        userId: this.reclamationList[index].userId,
        email: this.emails[index],
        subject: "Your feedback has been treated successfully!",
        content: header + response
      }
    }).subscribe(() => {
      this.reclamationList[index].reponse = response;
      this.addReclamation(this.reclamationList[index]);
    });


  }


  addReclamation(reclamation: Reclamation): void {

    this.reclamationService.addReclamation(reclamation).subscribe(
      response => {
        console.log('Reclamation added successfully:', response);
      },
    );
  }


}
