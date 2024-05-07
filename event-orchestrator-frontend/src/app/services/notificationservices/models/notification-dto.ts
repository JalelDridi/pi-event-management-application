/* tslint:disable */
/* eslint-disable */
export interface NotificationDto {
  content?: string;
  deliveryChannel?: 'email' | 'webNotification' | 'chatNotification' | 'Sms';
  email?: string;
  fullName?: string;
  groupId?: string;
  role?: string;
  subject?: string;
  userId?: string;
  userIdFrom?: string;
}
