/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Notification } from '../../models/notification';

export interface GetAllNotifications$Params {
}

export function getAllNotifications(http: HttpClient, rootUrl: string, params?: GetAllNotifications$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Notification>>> {
  const rb = new RequestBuilder(rootUrl, getAllNotifications.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<Notification>>;
    })
  );
}

getAllNotifications.PATH = '/notification/get-all-notif';
