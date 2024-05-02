/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addNotification } from '../fn/controller/add-notification';
import { AddNotification$Params } from '../fn/controller/add-notification';
import { countUnreadMessages } from '../fn/controller/count-unread-messages';
import { CountUnreadMessages$Params } from '../fn/controller/count-unread-messages';
import { countUnreadNotifications } from '../fn/controller/count-unread-notifications';
import { CountUnreadNotifications$Params } from '../fn/controller/count-unread-notifications';
import { getAllMessages } from '../fn/controller/get-all-messages';
import { GetAllMessages$Params } from '../fn/controller/get-all-messages';
import { getAllNotifications } from '../fn/controller/get-all-notifications';
import { GetAllNotifications$Params } from '../fn/controller/get-all-notifications';
import { getMessageById } from '../fn/controller/get-message-by-id';
import { GetMessageById$Params } from '../fn/controller/get-message-by-id';
import { getUserMessages } from '../fn/controller/get-user-messages';
import { GetUserMessages$Params } from '../fn/controller/get-user-messages';
import { getUserNotifications } from '../fn/controller/get-user-notifications';
import { GetUserNotifications$Params } from '../fn/controller/get-user-notifications';
import { getWebNotifs } from '../fn/controller/get-web-notifs';
import { GetWebNotifs$Params } from '../fn/controller/get-web-notifs';
import { Message } from '../models/message';
import { Notification } from '../models/notification';
import { NotificationUserDto } from '../models/notification-user-dto';
import { sendMessage } from '../fn/controller/send-message';
import { SendMessage$Params } from '../fn/controller/send-message';
import { sendNotification } from '../fn/controller/send-notification';
import { SendNotification$Params } from '../fn/controller/send-notification';
import { sendNotificationHtml } from '../fn/controller/send-notification-html';
import { SendNotificationHtml$Params } from '../fn/controller/send-notification-html';
import { setUserMessagesAsRead } from '../fn/controller/set-user-messages-as-read';
import { SetUserMessagesAsRead$Params } from '../fn/controller/set-user-messages-as-read';
import { testt } from '../fn/controller/testt';
import { Testt$Params } from '../fn/controller/testt';

@Injectable({ providedIn: 'root' })
export class ControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `setUserMessagesAsRead()` */
  static readonly SetUserMessagesAsReadPath = '/notification/set-messages-read/{userId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `setUserMessagesAsRead()` instead.
   *
   * This method doesn't expect any request body.
   */
  setUserMessagesAsRead$Response(params: SetUserMessagesAsRead$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return setUserMessagesAsRead(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `setUserMessagesAsRead$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  setUserMessagesAsRead(params: SetUserMessagesAsRead$Params, context?: HttpContext): Observable<void> {
    return this.setUserMessagesAsRead$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `sendNotificationHtml()` */
  static readonly SendNotificationHtmlPath = '/notification/send-notification-html';

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

  /** Path part for operation `sendMessage()` */
  static readonly SendMessagePath = '/notification/send-message';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `sendMessage()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendMessage$Response(params: SendMessage$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return sendMessage(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `sendMessage$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendMessage(params: SendMessage$Params, context?: HttpContext): Observable<void> {
    return this.sendMessage$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `sendNotification()` */
  static readonly SendNotificationPath = '/notification/confirm-user';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `sendNotification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendNotification$Response(params: SendNotification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return sendNotification(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `sendNotification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendNotification(params: SendNotification$Params, context?: HttpContext): Observable<void> {
    return this.sendNotification$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `addNotification()` */
  static readonly AddNotificationPath = '/notification/add-notif';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addNotification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addNotification$Response(params: AddNotification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return addNotification(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addNotification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  addNotification(params: AddNotification$Params, context?: HttpContext): Observable<void> {
    return this.addNotification$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `testt()` */
  static readonly TesttPath = '/notification/send-upcoming-events';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `testt()` instead.
   *
   * This method doesn't expect any request body.
   */
  testt$Response(params?: Testt$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<NotificationUserDto>>> {
    return testt(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `testt$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  testt(params?: Testt$Params, context?: HttpContext): Observable<Array<NotificationUserDto>> {
    return this.testt$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<NotificationUserDto>>): Array<NotificationUserDto> => r.body)
    );
  }

  /** Path part for operation `getWebNotifs()` */
  static readonly GetWebNotifsPath = '/notification/get-web-notifications/{userId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getWebNotifs()` instead.
   *
   * This method doesn't expect any request body.
   */
  getWebNotifs$Response(params: GetWebNotifs$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Message>>> {
    return getWebNotifs(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getWebNotifs$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getWebNotifs(params: GetWebNotifs$Params, context?: HttpContext): Observable<Array<Message>> {
    return this.getWebNotifs$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Message>>): Array<Message> => r.body)
    );
  }

  /** Path part for operation `getUserNotifications()` */
  static readonly GetUserNotificationsPath = '/notification/get-user-notif';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getUserNotifications()` instead.
   *
   * This method doesn't expect any request body.
   */
  getUserNotifications$Response(params: GetUserNotifications$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Notification>>> {
    return getUserNotifications(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getUserNotifications$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getUserNotifications(params: GetUserNotifications$Params, context?: HttpContext): Observable<Array<Notification>> {
    return this.getUserNotifications$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Notification>>): Array<Notification> => r.body)
    );
  }

  /** Path part for operation `getUserMessages()` */
  static readonly GetUserMessagesPath = '/notification/get-user-messages';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getUserMessages()` instead.
   *
   * This method doesn't expect any request body.
   */
  getUserMessages$Response(params?: GetUserMessages$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Message>>> {
    return getUserMessages(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getUserMessages$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getUserMessages(params?: GetUserMessages$Params, context?: HttpContext): Observable<Array<Message>> {
    return this.getUserMessages$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Message>>): Array<Message> => r.body)
    );
  }

  /** Path part for operation `getMessageById()` */
  static readonly GetMessageByIdPath = '/notification/get-message-by-id/{messageId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getMessageById()` instead.
   *
   * This method doesn't expect any request body.
   */
  getMessageById$Response(params: GetMessageById$Params, context?: HttpContext): Observable<StrictHttpResponse<Message>> {
    return getMessageById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getMessageById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getMessageById(params: GetMessageById$Params, context?: HttpContext): Observable<Message> {
    return this.getMessageById$Response(params, context).pipe(
      map((r: StrictHttpResponse<Message>): Message => r.body)
    );
  }

  /** Path part for operation `getAllNotifications()` */
  static readonly GetAllNotificationsPath = '/notification/get-all-notif';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllNotifications()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllNotifications$Response(params?: GetAllNotifications$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Notification>>> {
    return getAllNotifications(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllNotifications$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllNotifications(params?: GetAllNotifications$Params, context?: HttpContext): Observable<Array<Notification>> {
    return this.getAllNotifications$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Notification>>): Array<Notification> => r.body)
    );
  }

  /** Path part for operation `getAllMessages()` */
  static readonly GetAllMessagesPath = '/notification/get-all-msgs';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllMessages()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllMessages$Response(params?: GetAllMessages$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Message>>> {
    return getAllMessages(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllMessages$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllMessages(params?: GetAllMessages$Params, context?: HttpContext): Observable<Array<Message>> {
    return this.getAllMessages$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Message>>): Array<Message> => r.body)
    );
  }

  /** Path part for operation `countUnreadNotifications()` */
  static readonly CountUnreadNotificationsPath = '/notification/count-unread-notif';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `countUnreadNotifications()` instead.
   *
   * This method doesn't expect any request body.
   */
  countUnreadNotifications$Response(params: CountUnreadNotifications$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return countUnreadNotifications(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `countUnreadNotifications$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  countUnreadNotifications(params: CountUnreadNotifications$Params, context?: HttpContext): Observable<number> {
    return this.countUnreadNotifications$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `countUnreadMessages()` */
  static readonly CountUnreadMessagesPath = '/notification/count-unread-messages';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `countUnreadMessages()` instead.
   *
   * This method doesn't expect any request body.
   */
  countUnreadMessages$Response(params?: CountUnreadMessages$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return countUnreadMessages(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `countUnreadMessages$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  countUnreadMessages(params?: CountUnreadMessages$Params, context?: HttpContext): Observable<number> {
    return this.countUnreadMessages$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

}
