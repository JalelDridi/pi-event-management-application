import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'})
export class QrCodeService {
  private qrDataSubject = new BehaviorSubject<string>(''); // Initial value is an empty string
  qrdata1$ = this.qrDataSubject.asObservable(); // Expose as observable for subscribers

  constructor() { }

  updateQrData(newData: string) {
    console.log('Updating data:', newData);
    this.qrDataSubject.next(newData); // Update the value and notify subscribers
  }
}
