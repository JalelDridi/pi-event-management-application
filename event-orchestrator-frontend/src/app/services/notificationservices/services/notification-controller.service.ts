/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addNotification } from '../fn/notification-controller/add-notification';
import { AddNotification$Params } from '../fn/notification-controller/add-notification';
import { confirmParticipation } from '../fn/notification-controller/confirm-participation';
import { ConfirmParticipation$Params } from '../fn/notification-controller/confirm-participation';
import { countUnreadMessages } from '../fn/notification-controller/count-unread-messages';
import { CountUnreadMessages$Params } from '../fn/notification-controller/count-unread-messages';
import { countUnreadNotifications } from '../fn/notification-controller/count-unread-notifications';
import { CountUnreadNotifications$Params } from '../fn/notification-controller/count-unread-notifications';
import { deleteNotification } from '../fn/notification-controller/delete-notification';
import { DeleteNotification$Params } from '../fn/notification-controller/delete-notification';
import { getAllMessages } from '../fn/notification-controller/get-all-messages';
import { GetAllMessages$Params } from '../fn/notification-controller/get-all-messages';
import { getAllNotifications } from '../fn/notification-controller/get-all-notifications';
import { GetAllNotifications$Params } from '../fn/notification-controller/get-all-notifications';
import { getChatMessages } from '../fn/notification-controller/get-chat-messages';
import { GetChatMessages$Params } from '../fn/notification-controller/get-chat-messages';
import { getWebNotifs } from '../fn/notification-controller/get-web-notifs';
import { GetWebNotifs$Params } from '../fn/notification-controller/get-web-notifs';
import { Message } from '../models/message';
import { Notification } from '../models/notification';
import { sendMessage } from '../fn/notification-controller/send-message';
import { SendMessage$Params } from '../fn/notification-controller/send-message';
import { sendWebNotification } from '../fn/notification-controller/send-web-notification';
import { SendWebNotification$Params } from '../fn/notification-controller/send-web-notification';
import { setChatMessagesAsRead } from '../fn/notification-controller/set-chat-messages-as-read';
import { SetChatMessagesAsRead$Params } from '../fn/notification-controller/set-chat-messages-as-read';
import { setUserNotificationsAsRead } from '../fn/notification-controller/set-user-notifications-as-read';
import { SetUserNotificationsAsRead$Params } from '../fn/notification-controller/set-user-notifications-as-read';

@Injectable({ providedIn: 'root' })
export class NotificationControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `setChatMessagesAsRead()` */
  static readonly SetChatMessagesAsReadPath = '/set-messages-read/{userId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `setChatMessagesAsRead()` instead.
   *
   * This method doesn't expect any request body.
   */
  setChatMessagesAsRead$Response(params: SetChatMessagesAsRead$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return setChatMessagesAsRead(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `setChatMessagesAsRead$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  setChatMessagesAsRead(params: SetChatMessagesAsRead$Params, context?: HttpContext): Observable<void> {
    return this.setChatMessagesAsRead$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `sendWebNotification()` */
  static readonly SendWebNotificationPath = '/send-web-notification';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `sendWebNotification()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendWebNotification$Response(params: SendWebNotification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return sendWebNotification(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `sendWebNotification$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  sendWebNotification(params: SendWebNotification$Params, context?: HttpContext): Observable<void> {
    return this.sendWebNotification$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `sendMessage()` */
  static readonly SendMessagePath = '/send-message';

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

  /** Path part for operation `confirmParticipation()` */
  static readonly ConfirmParticipationPath = '/confirm-participation';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `confirmParticipation()` instead.
   *
   * This method doesn't expect any request body.
   */
  confirmParticipation$Response(params: ConfirmParticipation$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return confirmParticipation(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `confirmParticipation$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  confirmParticipation(params: ConfirmParticipation$Params, context?: HttpContext): Observable<void> {
    return this.confirmParticipation$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `addNotification()` */
  static readonly AddNotificationPath = '/add-notif';

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

  /** Path part for operation `setUserNotificationsAsRead()` */
  static readonly SetUserNotificationsAsReadPath = '/set-notifications-read';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `setUserNotificationsAsRead()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  setUserNotificationsAsRead$Response(params: SetUserNotificationsAsRead$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return setUserNotificationsAsRead(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `setUserNotificationsAsRead$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  setUserNotificationsAsRead(params: SetUserNotificationsAsRead$Params, context?: HttpContext): Observable<void> {
    return this.setUserNotificationsAsRead$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getWebNotifs()` */
  static readonly GetWebNotifsPath = '/get-web-notifications/{userId}';

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

  /** Path part for operation `getChatMessages()` */
  static readonly GetChatMessagesPath = '/get-user-chat-messages';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getChatMessages()` instead.
   *
   * This method doesn't expect any request body.
   */
  getChatMessages$Response(params?: GetChatMessages$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<Message>>> {
    return getChatMessages(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getChatMessages$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getChatMessages(params?: GetChatMessages$Params, context?: HttpContext): Observable<Array<Message>> {
    return this.getChatMessages$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<Message>>): Array<Message> => r.body)
    );
  }

  /** Path part for operation `getAllNotifications()` */
  static readonly GetAllNotificationsPath = '/get-all-notif';

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
  static readonly GetAllMessagesPath = '/get-all-msgs';

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
  static readonly CountUnreadNotificationsPath = '/count-unread-notif';

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
  static readonly CountUnreadMessagesPath = '/count-unread-messages';

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

  /** Path part for operation `deleteNotification()` */
  static readonly DeleteNotificationPath = '/delete-notification/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteNotification()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteNotification$Response(params: DeleteNotification$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteNotification(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteNotification$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteNotification(params: DeleteNotification$Params, context?: HttpContext): Observable<void> {
    return this.deleteNotification$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
