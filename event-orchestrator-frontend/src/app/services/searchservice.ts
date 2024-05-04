import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private keywordSubject = new BehaviorSubject<string>('');

  constructor() { }

  setKeyword(keyword: string): void {
    this.keywordSubject.next(keyword);
  }

  getKeyword(): Observable<string> {
    return this.keywordSubject.asObservable();
  }
}
