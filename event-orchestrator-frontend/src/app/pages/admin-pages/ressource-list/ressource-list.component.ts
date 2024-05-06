import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ServiceRessourceService} from "../../../services/adminservices/ServiceRessource/service-ressource.service";
import { jsPDF } from 'jspdf';

@Component({
  selector: 'app-ressource-list',
  templateUrl: './ressource-list.component.html',
  styleUrls: ['./ressource-list.component.css']
})
export class RessourceListComponent implements OnInit{

  constructor(private ressourceService: ServiceRessourceService) { }

  @ViewChild('table') table: ElementRef;
  UserRessource: any[] = [];

  ngOnInit(): void {
    this.ressourceService.getALLRessources().subscribe((ressources: any[]) => {
      this.UserRessource = ressources;
    });
    this.stat();
  }

  stat(): void {
    this.ressourceService.calculateAvailabilityPercentage().subscribe((percentage) => {
      document.getElementById("Resource-percentage").innerHTML = percentage + "%";
    });
  }

  downloadPDF(): void {
    this.ressourceService.getALLRessources().subscribe((ressources: any[]) => {
      if (!ressources || ressources.length === 0) {
        console.error('UserRessource is empty or undefined.');
        return;
      }
      console.log(ressources);

      const doc = new jsPDF();
      const table = this.generateTable(ressources);

      console.log(table); // Add this line for debugging

      doc.text('Ressource List', 10, 10);

      if (!table) {
        console.error('Table is empty or undefined.');
        return;
      }
                                                                            
      try {
        (doc as any).autoTable({ html: table });
      } catch (error) {
        console.error('Error while generating autoTable:', error);
      }

      doc.save('RessourceList.pdf');
    });
  }

  generateTable(ressources: any[]): HTMLElement {
    const table = document.createElement('table');
    table.className = 'table align-items-center table-flush';

    const thead = document.createElement('thead');
    thead.className = 'thead-light';
    const trHead = document.createElement('tr');
    const headers = ['Id', 'Type', 'Quantité', 'Nombre de Réservations'];

    headers.forEach(headerText => {
      const th = document.createElement('th');
      th.textContent = headerText;
      trHead.appendChild(th);
    });

    thead.appendChild(trHead);
    table.appendChild(thead);

    const tbody = document.createElement('tbody');

    ressources.forEach(ressource => {
      const tr = document.createElement('tr');
      headers.forEach(header => {
        const td = document.createElement('td');
        td.textContent = ressource[header.toLowerCase()] || ''; // Assuming your data keys are lowercase versions of headers
        tr.appendChild(td);
      });
      tbody.appendChild(tr);
    });

    table.appendChild(tbody);

    return table;
  }
}
