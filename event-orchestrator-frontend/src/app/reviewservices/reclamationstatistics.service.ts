import { Reclamation } from './../ReviewModels/Reclamation.Model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReclamationStatisticsService {

  public getReclamationByType(reclamations: Reclamation[]): { [type: string]: number } {
    return reclamations.reduce((acc, curr) => {
      const type = curr.typeRec.toString(); 
      acc[type] = (acc[type] || 0) + 1;
      return acc;
    }, {});
  }

  public getMostReclamationsByRessourceID(reclamations: Reclamation[]): number | null {
    if (reclamations.length === 0) return null;
    const ressourceReclamationsCount = reclamations.reduce((acc, curr) => {
      // Check if ressourceID is not null or undefined
      if (curr.ressourceId!== null && curr.ressourceId!== undefined) {
        const ressourceID = curr.ressourceId.toString();
        acc[ressourceID] = (acc[ressourceID] || 0) + 1;
      }
      return acc;
    }, {});
    const maxCount = Math.max(...Object.values<number>(ressourceReclamationsCount) || [0]);
    // Find the ressourceID with the maximum count
    const maxKey = Object.keys(ressourceReclamationsCount).find(key => ressourceReclamationsCount[key] === maxCount);
    return maxKey? parseInt(maxKey) : null;
  }

  public getMostReclamationsByEventID(reclamations: Reclamation[]): number | null {
    if (reclamations.length === 0) return null;
    const eventReclamationsCount = reclamations.reduce((acc, curr) => {
      // Check if eventId is not null or undefined
      if (curr.eventId!== null && curr.eventId!== undefined) {
        const eventId = curr.eventId.toString();
        acc[eventId] = (acc[eventId] || 0) + 1;
      }
      return acc;
    }, {});
    const maxCount = Math.max(...Object.values<number>(eventReclamationsCount) || [0]);
    // Find the eventId with the maximum count
    const maxKey = Object.keys(eventReclamationsCount).find(key => eventReclamationsCount[key] === maxCount);
    return maxKey? parseInt(maxKey) : null;
  }
  public getUserWithMostReclamations(reclamations: Reclamation[]): string | null {
    if (reclamations.length === 0) return null;
    const userReclamationsCount = reclamations.reduce((acc, curr) => {
      const userId = curr.userId; // Assuming userId is a string
      acc[userId] = (acc[userId] || 0) + 1;
      return acc;
    }, {});
    const maxCount = Math.max(...Object.values<number>(userReclamationsCount));
    return Object.keys(userReclamationsCount).find(key => userReclamationsCount[key] === maxCount);
  }
  public getRespondedReclamationsPercentage(reclamations: Reclamation[]): number {
    if (reclamations.length === 0) return 0;
    const respondedReclamationsCount = reclamations.filter(reclamation => reclamation.reponse).length;
    const totalReclamations = reclamations.length;
    const percentage = (respondedReclamationsCount / totalReclamations) * 100;
    return percentage;
  }
}

