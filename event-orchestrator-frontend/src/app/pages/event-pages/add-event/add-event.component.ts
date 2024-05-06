import {Component, OnInit} from '@angular/core';
import {EventService} from "../../../services/eventservices/eventservice/event.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {MapserviceService} from "../../../services/eventservices/eventservice/mapservice.service";


interface EventType {
  value: string;
  viewValue: string;}

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.scss']
})
export class AddEventComponent implements OnInit{

  event: any = {
    name: '',
    description: '',
    startDate: null,
    endDate: null,
    eventType: '',
    Club: '',
    image: File,
    lng:Float64Array,
    lat:Float64Array
  };
  userid:String='6631182424b2edcedeb7478d';
  placeName:String=''

  constructor(private Event : EventService,private route:Router ,  private toastr: ToastrService, private geocodingService:MapserviceService) { }

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
    if (this.event.image) {
      formData.append('file', this.event.image);
    } else {
      // Handle cases where file is not selected
      alert('Please select a file to upload.');
      return;
    }

    formData.append('name', this.event.name);
    formData.append('description', this.event.description);
    formData.append('eventType', this.event.eventType);
    formData.append('Club', this.event.Club);

    this.Event.addEvent(formData, this.userid).subscribe(
      response => {
        this.toastr.success('Event successfully added!');
        this.route.navigate(['/Events']);
      },
      error => {
        console.error('Error adding event:', error);
        this.toastr.error("Erreur lors de l'ajout de l'événement");
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
