import { Question } from "./question.model";

export interface Quiz {
    id?: number; // Optional because it might not exist before creation
    name: string;
    description: string;
    startDate: Date;
    questions: Question[];
  }