import { Component, OnInit } from '@angular/core';
import {RegistrationRequest} from "../../userservices/models/registration-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../userservices/services/authentication.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerRequest: RegistrationRequest = {email: '', firstName: '', lastName: '', password: ''};
  errorMsg: Array<string> = [];
  constructor(
    private router: Router,
    private authService: AuthenticationService) { }

  login() {
    this.router.navigate(['login']);
  }

  register() {
    this.errorMsg = [];
    this.authService.register({
      body: this.registerRequest
    })
      .subscribe({
        next: () => {
          this.router.navigate(['activate-account']);
        },
        error: (err) => {
          this.errorMsg = err.error.validationErrors;
        }
      });
  }

  ngOnInit() {
  }

}
