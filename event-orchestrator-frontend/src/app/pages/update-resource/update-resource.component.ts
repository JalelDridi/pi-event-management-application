import { Component } from '@angular/core';
import { Resource } from '../resource-list/resource';
import { ResourceService } from '../resource-list/resource.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-resource',
  templateUrl: './update-resource.component.html',
  styleUrls: ['./update-resource.component.css']
})
export class UpdateResourceComponent {
  resourceID: number;
  resource : Resource = {resourceID:0,resourceName:'',isAvailable:false,date:new Date()} ;
  submitted = false;

  constructor(private route: ActivatedRoute,private router: Router,
    private resourceService: ResourceService) { }

  // ngOnInit() {
  //   this.resource = new Resource();

  //   this.resourceID = this.route.snapshot.params['id'];
    
  //   this.resourceService.getResource(this.resourceID)
  //     .subscribe(data => {
  //       console.log(data)
  //       this.resource = data;
  //     }, error => console.log(error));
  // }
  ngOnInit(): void {
   /* this.route.paramMap.subscribe(params => {
      const id = params.get('resourceID');
      if (id !== null) {
        this.resourceID = +id; // Si vous préférez, vous pouvez utiliser + pour forcer la conversion
        this.getResource(this.resourceID);
      }
    });*/
    this.resourceID= this.route.snapshot.params['resourceID']
    this.resource = { resourceID: 0, resourceName: '', isAvailable:false ,date: new Date() }; // quiz_id sera un nombre, donc initialisé à 0
  }
  getResource(resourceID: number): void {
    this.resourceService.getResource(resourceID).subscribe(
      (resource:Resource) => {
        this.resource = resource;
      },
      (error) => {
        console.error('Erreur lors de la récupération du quiz :', error);
      }
    );
  }


  updateResource():void {
    console.log('test id: '+ this.resourceID);
    console.log(this.route.snapshot.params);
    
    this.resourceService.updateResource(this.resourceID,this.resource)
      .subscribe((response) => {console.log('resource updated successfully',response);
       this.router.navigate(['/ResourceList'])
      }, (error) => {console.log(error)});
    // this.resource = new Resource();
   // this.gotoList();
  }

  onSubmit() {
    this.updateResource();    
  }

}
