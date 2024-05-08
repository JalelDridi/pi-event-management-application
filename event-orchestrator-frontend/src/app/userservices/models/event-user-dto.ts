/* tslint:disable */
/* eslint-disable */
import {Role} from "./role";

export interface EventUserDto {
  email?: string;
  firstName?: string;
  lastName?: string;
  userID?: string;
  roles?: Array<Role>;
}
