/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { Message } from '../../models/message';

export interface GetChatMessages$Params {
}

export function getChatMessages(http: HttpClient, rootUrl: string, params?: GetChatMessages$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Message>>> {
  const rb = new RequestBuilder(rootUrl, getChatMessages.PATH, 'get');
  if (params) {
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

getChatMessages.PATH = '/get-user-chat-messages';
