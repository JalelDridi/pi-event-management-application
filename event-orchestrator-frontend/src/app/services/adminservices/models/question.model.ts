import { Option } from "./option.model";
export interface Question {
    id?: number;
    question: string;
    difficulty: 'EASY' | 'MEDIUM' | 'DIFFICULT'; // Enum types
    options: Option[];
  }