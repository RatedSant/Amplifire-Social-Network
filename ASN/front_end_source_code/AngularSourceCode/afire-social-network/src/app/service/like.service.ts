import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IASNHttpResponse } from '../model/asnhttpresponse';
import { ILike } from '../model/like';
import { ICountResponse, IResultResponse } from '../model/resultResponse';
import { API_URL } from '../shared/global-variables';

@Injectable({
  providedIn: 'root'
})
export class LikeService {



  constructor(private httpClient: HttpClient) { }

  saveLikeRequest(asnUserFk: number, asnPostFk: number): void {
    //console.log('You have entered');
    let like: ILike = {
      asnUserFk: asnUserFk,
      asnPostFk: asnPostFk
    }
    this.httpClient.post<IASNHttpResponse>(API_URL + '/api/like/add', like,
      { withCredentials: true }).subscribe(data => {
        console.log(data);
      });
  }

  deleteLikeRequest(asnUserFk: number, asnPostFk: number): void {
    let options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: {
        asnUserFk: asnUserFk,
        asnPostFk: asnPostFk
      },
    };

    this.httpClient.delete<IASNHttpResponse>(API_URL + '/api/like/delete', options).subscribe(data => {
      console.log(data);
    });
  }

  toggleLikeRequest(asnUserFk: number, asnPostFk: number, hasLiked: boolean): boolean {
    if (hasLiked) {
      //console.log('Delete a like');
      this.deleteLikeRequest(asnUserFk, asnPostFk);
      return false;
    }
    else {
      //console.log('Saving a like');
      this.saveLikeRequest(asnUserFk, asnPostFk);
      return true;
    }
  }

  hasLike(asnUserId: number, asnPostId: number): Observable<IResultResponse> {
    return this.httpClient.get<IResultResponse>(API_URL + '/api/like/' + asnUserId + '/' + asnPostId, { withCredentials: true });
  }

  getCount(asnPostId: number) {
    return this.httpClient.get<ICountResponse>(API_URL + '/api/like/count/' + asnPostId, { withCredentials: true });
  }
}
