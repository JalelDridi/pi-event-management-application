import { TypeReclamation } from './type-reclamation.enum';
 
export class Reclamation {
  IdRec?: number;
  eventId?: number;
  ressourceID?:number;
  userId: string;
  content : string;
  typeRec: TypeReclamation;
  dateReclamation: Date;
 
  constructor(
    eventId: number,
    userId: string,
    ressoruceID: number,
    content : string,
    typeRec: TypeReclamation,
    dateReclamation: Date
  ) {
    this.eventId = eventId;
    this.userId = userId;
    this.ressourceID = ressoruceID;
    this.content = content;
    this.typeRec = typeRec;
    this.dateReclamation = dateReclamation;
  }
}