/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface ConfirmParticipation$Params {
  userId: string;
  eventId: number;
  Authorization: string;
}

export function confirmParticipation(http: HttpClient, rootUrl: string, params: ConfirmParticipation$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, confirmParticipation.PATH, 'post');
  if (params) {
    rb.query('userId', params.userId, {});
    rb.query('eventId', params.eventId, {});
    rb.header('Authorization', params.Authorization, {});
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

confirmParticipation.PATH = '/notification/confirm-participation';
