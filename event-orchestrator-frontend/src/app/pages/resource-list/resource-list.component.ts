import { Component } from '@angular/core';
import { Resource } from './resource';
import { ResourceService } from './resource.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-resource-list',
  templateUrl: './resource-list.component.html',
  styleUrls: ['./resource-list.component.css']
})
export class ResourceListComponent {

  resources: Resource[] =[];

  constructor(private resourceService: ResourceService, private router: Router) { }

//   ngOnInit(): void {
//     this.resourceService.getResources()
//         .subscribe(
//             res => {
//                 this.posts = res as Resource[]; // Cast the response to Question[]
//             },
//             error => {
//                 console.error(error);
//             }
//         );
// }
ngOnInit() {
this.getResources();
}
getResources(){
  this.resourceService.getResources().subscribe(
    res => {
      console.log(res); // Log the response to the console
      this.resources = res as Resource[]; // Assign the response to your posts array
    },
    error => {
      console.error(error); 
      // Log any errors to the console
    }
  );
}

deleteResource(id: number) {
  this.resourceService.deleteResource(id)
    .subscribe(
      data => {
        console.log(data);
        this.getResources();
      },
      error => console.log(error));
}

updateResource(resource:Resource){
  this.router.navigate(['update', resource]);
}

}
