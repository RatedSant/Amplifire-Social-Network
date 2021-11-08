import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { IASNHttpResponse } from "../model/asnhttpresponse"
import { IUser } from '../model/user';
import { API_URL } from '../shared/global-variables';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private isloggedIn: boolean;

  constructor(private myHttpCli: HttpClient) {
    this.isloggedIn = false;
  }

  loginRequest(username: string, password: string): Observable<IUser> {
    let user: IUser = {
      asnUser: username,
      asnUserPassword: password
    }
    return this.myHttpCli.post<IUser>(API_URL+'/api/user/login', user,
      { withCredentials: true });
  }

  userInfoRequest(): Observable<IUser> {
    return this.myHttpCli.get<IUser>(API_URL+'/api/user/session',
      {withCredentials: true});
  }

  logoutRequest(): Observable<IASNHttpResponse> {
    return this.myHttpCli.post<IASNHttpResponse>(API_URL+'/api/user/logout', {},
      {withCredentials: true});
  }

  setIsUserLoggeIn(loggedIn: boolean){
    this.isloggedIn = loggedIn;
  }

  getIsUserLoggedIn(): boolean {
    return this.isloggedIn;
  }
}