import { TypeReclamation } from './type-reclamation.enum';

export class Reclamation {
  idRec?: number;
  eventId?: number;
  ressourceId?:number;
  userId: string;
  content : string;
  reponse? : string;
  typeRec: TypeReclamation;
  dateReclamation: Date;

  constructor(
    idRec: number,
    eventId: number,
    userId: string,
    ressoruceId: number,
    content : string,
    reponse : string,
    typeRec: TypeReclamation,
    dateReclamation: Date
  ) {
    this.idRec = idRec;
    this.eventId = eventId;
    this.userId = userId;
    this.ressourceId = ressoruceId;
    this.content = content;
    this.reponse = reponse;
    this.typeRec = typeRec;
    this.dateReclamation = dateReclamation;
  }
}
