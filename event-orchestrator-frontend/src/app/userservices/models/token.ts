/* tslint:disable */
/* eslint-disable */
import { User } from '../models/user';
export interface Token {
  createdAt?: string;
  expiresAt?: string;
  id?: string;
  token?: string;
  user?: User;
  validatedAt?: string;
}
