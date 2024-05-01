/* tslint:disable */
/* eslint-disable */
import { GrantedAuthority } from '../models/granted-authority';
import { Role } from '../models/role';
import { Token } from '../models/token';
export interface User {
  accountLocked?: boolean;
  accountNonExpired?: boolean;
  accountNonLocked?: boolean;
  authorities?: Array<GrantedAuthority>;
  createdDate?: string;
  credentialsNonExpired?: boolean;
  dateOfBirth?: string;
  email?: string;
  enabled?: boolean;
  firstName?: string;
  fullName?: string;
  lastModifiedDate?: string;
  lastName?: string;
  name?: string;
  password?: string;
  role?: Role;
  roles?: Array<Role>;
  tokens?: Array<Token>;
  userID?: string;
  username?: string;
}
