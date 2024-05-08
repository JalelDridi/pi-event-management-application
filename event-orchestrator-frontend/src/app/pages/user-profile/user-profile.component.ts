import { Component, OnInit } from '@angular/core';
import { EventUserDto } from "../../userservices/models/event-user-dto";
import { UserService } from "../../userservices/services/user.service";
import {User} from "../../userservices/models/user";
import {UpdateUser$Params} from "../../userservices/fn/user/update-user";
import {th} from "date-fns/locale";
import {Role} from "../../userservices/models/role";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  user: EventUserDto; // Define a variable to store user information

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    // Retrieve the user ID from local storage or a service after login
    const userId = localStorage.getItem('userId'); // Assuming user ID is stored in local storage

    if (userId) {
      // If user ID is available, fetch user information
      this.userService.getUserById({ userId }).subscribe(
        (user: EventUserDto) => {
          this.user = user; // Set the fetched user information
        },
        (error) => {
          console.error('Error fetching user information:', error);
        }
      );
    } else {
      console.error('User ID not found');
    }
  }

  editProfile(): void {
    // Define the parameters for the updateUser function
    const params: UpdateUser$Params = {
      userId: this.user.userID,
      body: this.user
    };
    let role: Role = {
      createdDate: '2022-01-01',
      id: '1',
      lastModifiedDate: '2022-01-02',
      name: 'User'
    };
    this.user.roles.push()
    // Call the updateUser function
    this.userService.updateUser(params).subscribe(
      (updatedUser: User) => {
        // Handle the updated user information
        this.user = updatedUser;
      },
      (error) => {
        console.error('Error updating user information:', error);
      }
    );
  }
}
