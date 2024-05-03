import { Injectable } from '@angular/core';
import * as profaneWordsModule from 'profane-words';

@Injectable({
  providedIn: 'root'
})
export class BadWordsFilterService {

  constructor() {
  }

  containsBadWords(content: string): boolean {
    const lowerContent = content.toLowerCase();
    const profaneWords: string[] = profaneWordsModule;
    for (let i = 0; i < profaneWords.length; i++) {
      const word = profaneWords[i];
      if (lowerContent.includes(word.toLowerCase())) {
        return true;
      }
    }
    return false;
  }
}
