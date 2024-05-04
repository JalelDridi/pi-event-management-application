import { Component } from '@angular/core';
import {SearchService} from "../../services/searchservice";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  content: any[] = [];
  filteredContent: any[] = [];
  keyword: string = '';

  constructor(private searchService: SearchService) { }

  ngOnInit(): void {

  }

  filterContent(): void {
    this.filteredContent = this.content.filter(item => {/* TO DO */});
  }
}
