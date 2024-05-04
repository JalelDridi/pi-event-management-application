import {Component, OnInit} from '@angular/core';
import {UserFinalService} from "../../../services/adminservices/ServiceUserFinal/user-final.service";

@Component({
  selector: 'app-user-list-final',
  templateUrl: './user-list-final.component.html',
  styleUrls: ['./user-list-final.component.css']
})
export class UserListFinalComponent implements OnInit{

  UserList: any[] = [];

  constructor(private user: UserFinalService) { }

  ngOnInit(): void {
    this.user.findAllconfUsers().subscribe((users: any[]) => {
      this.UserList = users;
    });
  }

  toggleEmailInput(user: any): void {
    user.showEmailInput = !user.showEmailInput;
  }

  sendUpdateEmail(recipientEmail: string, mail: string): void {
    console.log(recipientEmail, mail);
    this.user.sendUpdateEmail(recipientEmail, mail).subscribe(
      response => {
        // Handle success response
        console.log('Mail sent successfully', response);
      },
      error => {
        // Handle error response
        console.error('Error sending mail:', error);
      }
    );
  }

}
