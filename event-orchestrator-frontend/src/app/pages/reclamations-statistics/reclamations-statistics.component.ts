import { Component, OnInit } from '@angular/core';
import { Reclamation } from 'src/app/ReviewModels/Reclamation.Model';
import { ReclamationService } from './../../reviewservices/reclamation.service';
import { ReclamationStatisticsService } from 'src/app/reviewservices/reclamationstatistics.service';
import { EChartsOption } from 'echarts';

@Component({
  selector: 'app-reclamations-statistics',
  templateUrl: './reclamations-statistics.component.html',
  styleUrls: ['./reclamations-statistics.component.css']
})
export class ReclamationsStatisticsComponent implements OnInit {
  reclamations: Reclamation[] = [];
  reclamationByType: { [type: string]: number } = {}; // Initialize as an empty object
  mostReclamationsByRessourceID: number | null = null;
  mostReclamationsByEventID: number | null = null;
  mostuserwithReclamations: string | null = null;
  pourcentageofresponse: number | null = null;
  chartOptions: EChartsOption;
  barOption: EChartsOption;

  constructor(private reclamationService: ReclamationService, private reclamationSatisticsService: ReclamationStatisticsService) { }

  ngOnInit(): void {
    this.loadReclamations();
  }

  loadReclamations(): void {
    this.reclamationService.getAllReclamations().subscribe(
      reclamations => {
        this.reclamations = reclamations;
        this.calculateStatistics();
      },
      error => {
        console.error('Error fetching reclamations:', error);
      }
    );
  }

  calculateStatistics(): void {
    this.reclamationByType = this.reclamationSatisticsService.getReclamationByType(this.reclamations);
    this.mostReclamationsByRessourceID = this.reclamationSatisticsService.getMostReclamationsByRessourceID(this.reclamations);
    this.mostReclamationsByEventID = this.reclamationSatisticsService.getMostReclamationsByEventID(this.reclamations);
    this.mostuserwithReclamations = this.reclamationSatisticsService.getUserWithMostReclamations(this.reclamations);
    this.pourcentageofresponse = this.reclamationSatisticsService.getRespondedReclamationsPercentage(this.reclamations);

    // Update chart options based on new data
    this.chartOptions = {
      title: {
        text: 'Reclamation Type Statistics',
        left: 'center', 
        top: 'bottom' 
    },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'right',
        top: 'center'
      },
      series: [
        {
          name: 'Reclamation Type',
          type: 'pie',
          radius: '50%',
          data: Object.keys(this.reclamationByType).map(key => ({ value: this.reclamationByType[key], name: key })),
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          },
          color: ['#cb2424', '#7ABA78', '#10439F'] 
        }
      ]
    };
    this.barOption = {
      title: {
        text: 'Reclamation Answer percentage',
        subtext: `Answer Rate:`,
        left: 'center', 
        top: 'bottom' 
      },
      yAxis: {
        type: 'value'
      },
      xAxis: {
        type: 'category',
        data: ['Replied', 'Unreplied']
      },
      series: [
        {
          data: [this.pourcentageofresponse, 100 - this.pourcentageofresponse], 
          type: 'bar',
          barWidth: '20%' 
        }
      ]
    };
  }
  
    
}