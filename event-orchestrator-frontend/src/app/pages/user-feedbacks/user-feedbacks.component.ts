import {Component, OnInit} from '@angular/core';
import {UserFinalService} from "../../services/adminservices/ServiceUserFinal/user-final.service";
import {ReclamationService} from "../../reviewservices/reclamation.service";
import {Reclamation} from "../../ReviewModels/Reclamation.Model";

@Component({
  selector: 'app-user-feedbacks',
  templateUrl: './user-feedbacks.component.html',
  styleUrls: ['./user-feedbacks.component.css']
})
export class UserFeedbacksComponent implements OnInit {

  UserList: any[] = [];
  reclamationList: Reclamation[] = [];

  constructor(private reclamationService: ReclamationService) { }

  ngOnInit(): void {
    this.reclamationService.getAllReclamations().subscribe(reclamations => {
      this.reclamationList = reclamations;
    });

    console.log("Hello");
    console.log(this.reclamationList);
    console.log("Hello");
  }

}
