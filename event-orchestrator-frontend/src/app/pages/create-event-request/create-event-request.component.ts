import {Component, OnInit} from '@angular/core';
import {EventService} from "../../services/eventservices/eventservice/event.service";
import {Router} from "@angular/router";
//import {ToastrService} from "ngx-toastr";
import {MapserviceService} from "../../services/eventservices/eventservice/mapservice.service";
import {SpringMailControllerService} from "../../services/notificationservices/services/spring-mail-controller.service";
import {NotificationDto} from "../../services/notificationservices/models/notification-dto";
import {UserService} from "../../userservices/services/user.service";
import {EventUserDto} from "../../userservices/models/event-user-dto";


interface EventType {
  value: string;
  viewValue: string;
}
@Component({
  selector: 'app-create-event-request',
  templateUrl: './create-event-request.component.html',
  styleUrls: ['./create-event-request.component.scss']
})
export class CreateEventRequestComponent implements OnInit{

  event: any = {
    name: '',
    description: '',
    status: "en_cours",
    startDate: null,
    endDate: null,
    eventType: '',
    Club: '',
    image: File,
    image1: File,
    lng:Float64Array,
    lat:Float64Array
  };
  userid:string= localStorage.getItem('userId');
  user: EventUserDto;
  placeName:String=''
  notificationDto: NotificationDto;
  constructor(private Event : EventService,
              private route:Router,
              //private toastr: ToastrService,
              private geocodingService:MapserviceService,
              private emailService: SpringMailControllerService,
              private userService: UserService) { }

  ngOnInit(): void {
  }
  EventType: EventType[] = [
    {value: 'Sportif-0', viewValue: 'Sportif'},
    {value: 'Culturelle-1', viewValue: 'Culturelle'},
    {value: 'sociale-2', viewValue: 'sociale'},
  ];
  ResourceType: EventType[] = [
    {value: 'Salle-0', viewValue: 'salle'},
    {value: 'Projecteur-1', viewValue: 'projecteur'},
    {value: 'emphie-2', viewValue: 'emphie'},
  ];
  Resource: EventType[] = [
    {value: '1-0', viewValue: 'salle'},
    {value: '2-1', viewValue: 'projecteur'},
    {value: '3-2', viewValue: 'emphie'},
  ];


  Add() {
    const formData = new FormData();
    if (!this.event.image) {
      alert('Please select a file to upload.');
      return;
    }
    formData.append('file', this.event.image);
    formData.append('file1', this.event.image1);
    formData.append('name', this.event.name);
    formData.append('description', this.event.description);
    formData.append('eventType', this.event.eventType);
    formData.append('Club', this.event.Club);


    this.Event.addEvent(formData, this.userid).subscribe(
      () => {
        this.route.navigate(['/Events']);
        this.userService.getUserById({ userId: this.userid }).subscribe(
          (user: EventUserDto) => {
            this.user = user;
            // Notify club representative
            const clubRepresentativeContent = `Hello, ${user.firstName} ${user.lastName}, your request to host an event has been sent. We'll inform you ASAP.`;
            this.emailService.setNotifyClubRepresentative({
              body: {
                userId: this.userid,
                email: user.email,
                subject: "Your event request has been sent",
                content: clubRepresentativeContent
              }
            }).subscribe(() => console.log('Successfully notified club representative'));

            // Notify admin
            const adminContent = `A new event request has been sent, please review it as soon as possible mr admin`;
          },
          (error) => {
            console.error('Error fetching user information:', error);
          }
        );
      }
    );
  }



  onSubmit() {
    this.Add();
  }

  onFileSelected(event: any) {
    if (event.target.files.length > 0) {
      this.event.image = event.target.files[0];
      console.log(this.event.image)
    }
  }

  onFileSelected1(event: any) {
    if (event.target.files.length > 0) {
      this.event.image1 = event.target.files[0];
      console.log(this.event.image1)
    }
  }
  getPlaceName(latitude: number, longitude: number): void {
    this.geocodingService.getPlaceName(latitude, longitude).subscribe(
      (data: any) => {
        if (data && data.display_name) {
          this.placeName = data.display_name;
        } else {
          this.placeName = 'Nom de la place non disponible';
        }
      },
      err => {
        console.log('Erreur lors de la récupération du nom de la place:', err);
        this.placeName = 'Erreur lors de la récupération du nom de la place';
      }
    );
  }

}
