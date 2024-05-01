import { Injectable } from '@angular/core';
import * as words from 'profane-words';

@Injectable({
  providedIn: 'root'
})
export class BadWordsFilterService {

  constructor() {
  }

  containsBadWords(content: string): boolean {
    const lowerContent = content.toLowerCase();
    for (const word of Object.keys(words)) {
      if (lowerContent.includes(word.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
}