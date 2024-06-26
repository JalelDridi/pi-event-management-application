/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Message } from '../../models/message';

export interface GetWebNotifs$Params {
  userId: string;
}

export function getWebNotifs(http: HttpClient, rootUrl: string, params: GetWebNotifs$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Message>>> {
  const rb = new RequestBuilder(rootUrl, getWebNotifs.PATH, 'get');
  if (params) {
    rb.path('userId', params.userId, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<Message>>;
    })
  );
}

getWebNotifs.PATH = '/get-web-notifications/{userId}';
