import { Injectable } from '@angular/core';

import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { IUser } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class BroadcastObjectService {

  //Store the login User
  private userSource = new BehaviorSubject<any>({}) 
  currentUser = this.userSource.asObservable()

  //broadcast current upload progress
  private uploadProgressSource = new BehaviorSubject<number>(0) 
  currentUpload = this.uploadProgressSource.asObservable()

  //broadcast URLs of uploaded files
  private filesURLsSource = new BehaviorSubject<any>([])
  currentURLs = this.filesURLsSource.asObservable()

  constructor() {    
   }

   broadcastUser(user: IUser){
     this.userSource.next(user)
   }

   broadcastUploadProgress(progress: number){
    this.uploadProgressSource.next(progress)
  }

  broadcastURLs(URLs: any){
    this.filesURLsSource.next(URLs)    
  }

}