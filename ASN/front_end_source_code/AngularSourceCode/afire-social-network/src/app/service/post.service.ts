import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_URL } from '../shared/global-variables';

import { IASNHttpResponse } from '../model/asnhttpresponse';
import { BroadcastObjectService } from '../shared/broadcast-object.service';
import { IPost } from '../model/post';
import { IUser } from '../model/user';
import { IComment } from '../model/comment';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(
    private httpClient: HttpClient
  ) {

  }

  savePostRequest(asnPostDescription: string, currentUser: IUser, images: string): Observable<IASNHttpResponse> {
    let post: IPost = {
      asnPostDescription: asnPostDescription,
      asnUserFK: currentUser,
      asnPostImageUrl: images
    }
    return this.httpClient.post<IASNHttpResponse>(API_URL + '/api/post/add', post,
      { withCredentials: true });
  }


  getPostRequest(): Observable<any> {
    return this.httpClient.get<any>(API_URL + '/api/post/all',
      { withCredentials: true });
  }

  getPostByUserId(userId: number): Observable<any> {
    return this.httpClient.get<any>(API_URL + '/api/post/user/' + userId,
      { withCredentials: true });
  }

  saveCommentRequest(commentDescription: string, post: IPost, currentUser: IUser): Observable<IASNHttpResponse> {
    let comment = {
      "asnCommentDescription": commentDescription,
      "asnPostFK": post,
      "asnUserFK": currentUser
    }
    return this.httpClient.post<IASNHttpResponse>(API_URL + '/api/post/comment', comment, { withCredentials: true })
  }

  getCommentByPostId(postId: number): Observable<IComment> {
    return this.httpClient.get<IComment>(API_URL + '/api/post/comment/' + postId, { withCredentials: true })
  }
}