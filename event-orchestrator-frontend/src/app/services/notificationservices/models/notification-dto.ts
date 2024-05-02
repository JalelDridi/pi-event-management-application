/* tslint:disable */
/* eslint-disable */
export interface NotificationDto {
  content?: string;
  deliveryChannel?: 'email' | 'webNotification' | 'Sms';
  email?: string;
  fullName?: string;
  groupId?: string;
  subject?: string;
  userId?: string;
  userIdFrom?: string;
}
