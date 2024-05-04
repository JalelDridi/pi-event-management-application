/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { confirmUserRegistration } from '../fn/spring-mail-controller/confirm-user-registration';
import { ConfirmUserRegistration$Params } from '../fn/spring-mail-controller/confirm-user-registration';
import { notifyAdmin } from '../fn/spring-mail-controller/notify-admin';
import { NotifyAdmin$Params } from '../fn/spring-mail-controller/notify-admin';
import { resetPassword } from '../fn/spring-mail-controller/reset-password';
import { ResetPassword$Params } from '../fn/spring-mail-controller/reset-password';
import { sendNotificationHtml } from '../fn/spring-mail-controller/send-notification-html';
import { SendNotificationHtml$Params } from '../fn/spring-mail-controller/send-notification-html';
import { sendUpcomingEvents } from '../fn/spring-mail-controller/send-upcoming-events';
import { SendUpcomingEvents$Params } from '../fn/spring-mail-controller/send-upcoming-events';
import { setNotifyClubRepresentative } from '../fn/spring-mail-controller/set-notify-club-representative';
import { SetNotifyClubRepresentative$Params } from '../fn/spring-mail-controller/set-notify-club-representative';

@Injectable({ providedIn: 'root' })
export class SpringMailControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `sendUpcomingEvents()` */
  static readonly SendUpcomingEventsPath = '/send-upcoming-events';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `sendUpcomingEvents()` instead.
   *
   * This method doesn't expect any request body.
   */
  sendUpcomingEvents$Response(params?: SendUpcomingEvents$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return sendUpcomingEvents(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `sendUpcomingEvents$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  sendUpcomingEvents(params?: SendUpcomingEvents$Params, context?: HttpContext): Observable<void> {
    return this.sendUpcomingEvents$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `sendNotificationHtml()` */
  static readonly SendNotificationHtmlPath = '/send-notification-html';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `sendNotificationHtml()` instead.
   *
   * This method doesn't expect any request body.
   */
  sendNotificationHtml$Response(params: SendNotificationHtml$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return sendNotificationHtml(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `sendNotificationHtml$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  sendNotificationHtml(params: SendNotificationHtml$Params, context?: HttpContext): Observable<void> {
    return this.sendNotificationHtml$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `resetPassword()` */
  static readonly ResetPasswordPath = '/reset-password';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `resetPassword()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  resetPassword$Response(params: ResetPassword$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return resetPassword(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `resetPassword$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  resetPassword(params: ResetPassword$Params, context?: HttpContext): Observable<void> {
    return this.resetPassword$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `notifyAdmin()` */
  static readonly NotifyAdminPath = '/event-request';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `notifyAdmin()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  notifyAdmin$Response(params: NotifyAdmin$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return notifyAdmin(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `notifyAdmin$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  notifyAdmin(params: NotifyAdmin$Params, context?: HttpContext): Observable<void> {
    return this.notifyAdmin$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `setNotifyClubRepresentative()` */
  static readonly SetNotifyClubRepresentativePath = '/event-request-response';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `setNotifyClubRepresentative()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  setNotifyClubRepresentative$Response(params: SetNotifyClubRepresentative$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return setNotifyClubRepresentative(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `setNotifyClubRepresentative$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  setNotifyClubRepresentative(params: SetNotifyClubRepresentative$Params, context?: HttpContext): Observable<void> {
    return this.setNotifyClubRepresentative$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `confirmUserRegistration()` */
  static readonly ConfirmUserRegistrationPath = '/confirm-user';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `confirmUserRegistration()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  confirmUserRegistration$Response(params: ConfirmUserRegistration$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return confirmUserRegistration(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `confirmUserRegistration$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  confirmUserRegistration(params: ConfirmUserRegistration$Params, context?: HttpContext): Observable<void> {
    return this.confirmUserRegistration$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
