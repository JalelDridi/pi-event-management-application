import { Component } from '@angular/core';
import { ResourceService } from '../resource-list/resource.service';
import { Resource } from '../resource-list/resource';
import { Router } from '@angular/router';
import { ResourceTypeService } from '../resource-type/resource-type.service';
import { ResourceType } from '../resource-type/resource-type';
import { ResourceStatistics } from './resource-statistics';


@Component({
  selector: 'app-ressources',
  templateUrl: './ressources.component.html',
  styleUrls: ['./ressources.component.css']
})
export class RessourcesComponent {

  resources: Resource[] =[];
  resourceTypeID: number;
  resourceStatistics: ResourceStatistics[];
  error: string;
  statisticsData: any;
  constructor(private resourceService: ResourceService, private router: Router , private resourceTypeService :ResourceTypeService) { }


ngOnInit(): void {
  this.loadResources();
  this.fetchStatistics();
}
fetchStatistics() {
  this.resourceService.getStatistics().subscribe(
    (data) => {
      this.statisticsData = data;
      // Calculate percentages
      this.calculatePercentages();
    },
    (error) => {
      this.error = error;
    }
  );
}

calculatePercentages() {
  if (this.statisticsData) {
    const totalCount = this.statisticsData.reduce((total: number, stat: any) => total + stat.resourceCount, 0);
    this.statisticsData.forEach((stat: any) => {
      stat.percentage = (stat.resourceCount / totalCount) * 100;
    });
  }
}

loadResources(): void {
  this.resourceService.getResources().subscribe(resources => this.resources = resources);
}

deleteResource(id: number) {
  this.resourceService.deleteResource(id)
    .subscribe(
      data => {
        console.log(data);
        this.loadResources();
      },
      error => console.log(error));
}

updateResource(resourceID:number){
  this.router.navigate(['update', resourceID]);
}



}
