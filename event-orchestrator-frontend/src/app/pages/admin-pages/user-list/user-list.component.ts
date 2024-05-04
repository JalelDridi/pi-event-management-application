import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../services/adminservices/ServiceUser/user.service";


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit{

  userPercentage: number = null;

  UserList: any[] = [];

  constructor(private user:UserService) { }

  ngOnInit(): void {
    this.user.getUserList().subscribe((users: any[]) => {
      this.UserList = users;
    });
    this.stat();
  }


  stat(): void {
    this.user.pourcentageUsersAuth().subscribe((percentage) => {
      document.getElementById("user-percentage").innerHTML = percentage + "%";
    });
  }


  deleteUser(id: string): void {
    console.log(id);
    this.user.deleteUser(id).subscribe(
      response => {
        // Handle success response
        console.log('User deleted successfully:', response);

      },
      error => {
        // Handle error response
        console.error('Error deleting user:', error);
      }
    );
  }

  AcceptUserCnx(id: string): void {
    console.log(id);
    this.user.AcceptUserCnx(id).subscribe(
      response => {
        // Handle success response
        console.log('User added successfully:', response);

      },
      error => {
        // Handle error response
        console.error('Error adding user:', error);
      }
    );
  }
}
