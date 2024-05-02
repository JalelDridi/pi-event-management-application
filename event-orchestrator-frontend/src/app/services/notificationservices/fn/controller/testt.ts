/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { NotificationUserDto } from '../../models/notification-user-dto';

export interface Testt$Params {
}

export function testt(http: HttpClient, rootUrl: string, params?: Testt$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<NotificationUserDto>>> {
  const rb = new RequestBuilder(rootUrl, testt.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<NotificationUserDto>>;
    })
  );
}

testt.PATH = '/notification/send-upcoming-events';
