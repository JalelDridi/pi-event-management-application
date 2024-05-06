/* tslint:disable */
/* eslint-disable */
export interface Notification {
  deliveryChannel?: 'email' | 'webNotification' | 'chatNotification' | 'Sms';
  isRead?: boolean;
  isSent?: boolean;
  messageId?: number;
  notificationId?: number;
  userId?: string;
}
