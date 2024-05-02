/* tslint:disable */
/* eslint-disable */
export interface Message {
  content?: string;
  groupId?: string;
  messageId?: number;
  messageType?: 'chatMessage' | 'groupMessage' | 'notification';
  read?: boolean;
  sentDate?: string;
  subject?: string;
  userId?: string;
  userIdFrom?: string;
}
