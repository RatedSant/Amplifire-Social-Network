import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IASNHttpResponse } from '../model/asnhttpresponse';
import { API_URL } from '../shared/global-variables';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  

  constructor(private httpClient: HttpClient) { }

  getUserListRequest(input: String): Observable<any> {
    return this.httpClient.get<any>(API_URL + '/api/user/search/' +input,
      { withCredentials: true });
  }

  recoverPassword(email: String): Observable<IASNHttpResponse> {

    return this.httpClient.get<IASNHttpResponse>(API_URL+`/api/user/recover/${email}`, 
      {withCredentials: true});

  }
}
