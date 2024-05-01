import { Pipe, PipeTransform } from '@angular/core';
import { formatDistance, format, parseISO } from 'date-fns';

@Pipe({
 name: 'customDate'
})
export class CustomDatePipe implements PipeTransform {

 transform(value: string): string {
    if (!value) {
      return '';
    }

    try {
      const date = parseISO(value);
      const now = new Date();

      if (date.toDateString() === now.toDateString()) {
        return formatDistance(date, now, { addSuffix: true });
      }

      const startOfWeek = now.getDate() - now.getDay();
      const endOfWeek = startOfWeek + 6;
      if (date >= new Date(now.setDate(startOfWeek)) && date <= new Date(now.setDate(endOfWeek))) {
        return formatDistance(date, now, { addSuffix: true });
      }

      return format(date, 'dd-MM-yy \'at\' HH:mm');
    } catch (error) {
      console.error('Error parsing or formatting date:', error);
      return '';
    }
 }
}
