import { Component } from '@angular/core';
import { ActivatedRoute,  Router } from '@angular/router';
import { ResourceService } from '../resource-list/resource.service';
import { Resource } from '../resource-list/resource';

@Component({
  selector: 'app-resource-list-by-type',
  templateUrl: './resource-list-by-type.component.html',
  styleUrls: ['./resource-list-by-type.component.css']
})
export class ResourceListByTypeComponent {
  resources: Resource[];

  constructor(private route: ActivatedRoute, private resourceService: ResourceService,private router : Router) { }

  ngOnInit(): void {
    const resourceTypeID = +this.route.snapshot.paramMap.get('resourceTypeID');
    this.fetchResourcesByType(resourceTypeID);
  }

  fetchResourcesByType(resourceTypeID: number) {
    this.resourceService.findResourcesByResourceType(resourceTypeID).subscribe((resources: Resource[]) => {
      this.resources = resources;
    }, error => {
      console.error(error);
    });
  }



}
