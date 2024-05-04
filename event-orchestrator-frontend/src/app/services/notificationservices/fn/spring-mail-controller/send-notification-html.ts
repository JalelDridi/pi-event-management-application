/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { NotificationDto } from '../../models/notification-dto';

export interface SendNotificationHtml$Params {
  notificationDto: NotificationDto;
}

export function sendNotificationHtml(http: HttpClient, rootUrl: string, params: SendNotificationHtml$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, sendNotificationHtml.PATH, 'post');
  if (params) {
    rb.query('notificationDto', params.notificationDto, {});
  }

  return http.request(
    rb.build({ responseType: 'text', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
    })
  );
}

sendNotificationHtml.PATH = '/send-notification-html';
