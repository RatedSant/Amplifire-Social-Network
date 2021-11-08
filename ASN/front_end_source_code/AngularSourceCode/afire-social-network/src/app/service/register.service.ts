import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IASNHttpResponse } from '../model/asnhttpresponse';

import { IUser, CUser } from '../model/user';
import { API_URL } from '../shared/global-variables';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(
    private httpClient: HttpClient
  ) { }
  
  registerUser (user: CUser, imageURL: string): Observable<IASNHttpResponse> {

    user.asnUserProfileImageUrl = imageURL;

    return this.httpClient.post<IASNHttpResponse>(API_URL+'/api/user/register', user,
    {withCredentials: true});

  }

  updateUser(user: IUser, imageURL: string): Observable<IUser> {

    user.asnUserProfileImageUrl = imageURL;

    return this.httpClient.post<IUser>(API_URL+'/api/user/update', user,
    {withCredentials: true});

  }

  getUserById(userID: number): Observable<IUser>{


    return this.httpClient.get<IUser>(API_URL+`/api/user/${userID}`, {withCredentials: true});

  }
}