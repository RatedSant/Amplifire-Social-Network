import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AngularFirestore, AngularFirestoreCollection, AngularFirestoreDocument } from '@angular/fire/compat/firestore';

import * as _ from 'lodash';
import { Upload } from '../model/upload';

import { getStorage, ref, uploadBytesResumable, getDownloadURL } from "firebase/storage";

import { BroadcastObjectService } from './broadcast-object.service';

@Injectable({
  providedIn: 'root'
})
export class UploadImageService {

  private URLs: any = [];

  private basePath: string = 'images/';

  uploadsCollection!: AngularFirestoreCollection<Upload>;
  uploads: Observable<Upload[]> | undefined;
  uploadsDoc: AngularFirestoreDocument<Upload> | undefined;

  constructor(
    private afs: AngularFirestore,
    private broadcastObject: BroadcastObjectService
  ) { }
 
  

  uploadFile(fileList: FileList): Promise<string>{
    return new Promise((resolve, reject) => {
      const storage = getStorage();            
      const metadata = {
        contentType: 'image/jpeg'
      };
      const storageRef = ref(storage, this.basePath + fileList[0].name);
      const uploadTask = uploadBytesResumable(storageRef, fileList[0], metadata);
      getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
        console.log('File available at', downloadURL);
        return resolve(downloadURL)                
      });      
   });       
  }

  

  /*pushUpload(fileList: FileList, index: number) {
    if (index < fileList.length) {
      
      const storage = getStorage();            
      const metadata = {
        contentType: 'image/jpeg'
      };
      // Upload file and metadata to the object 'images/mountains.jpg'
      const storageRef = ref(storage, 'images/' + fileList[index].name);
      const uploadTask = uploadBytesResumable(storageRef, fileList[index], metadata);

      getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
        console.log('File available at', downloadURL);
        this.URLs.push(downloadURL)      
        this.pushUpload(fileList, index + 1)        
      });

      // Listen for state changes, errors, and completion of the upload.
      uploadTask.on('state_changed',
        (snapshot) => {
          // Get task progress, including the number of bytes uploaded and the total number of bytes to be uploaded
          const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
          this.broadcastObject.broadcastUploadProgress(progress)
          console.log('Upload is ' + progress + '% done');
          switch (snapshot.state) {
            case 'paused':
              console.log('Upload is paused');
              break;
            case 'running':
              console.log('Upload is running');
              break;
            case 'success':
              console.log('Upload is success');
              this.broadcastObject.broadcastUploadProgress(0)
              break;
          }
        },
        (error) => {
          // A full list of error codes is available at
          // https://firebase.google.com/docs/storage/web/handle-errors
          switch (error.code) {
            case 'storage/unauthorized':
              // User doesn't have permission to access the object
              break;
            case 'storage/canceled':
              // User canceled the upload
              break;
            // ...
            case 'storage/unknown':
              // Unknown error occurred, inspect error.serverResponse
              break;
          }
        },
        () => {
          // Upload completed successfully, now we can get the download URL
          getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
            console.log('File available at', downloadURL);
            this.URLs.push(downloadURL)      
            this.pushUpload(fileList, index + 1)      
          });
        }
      );
    }
    else {
      console.log('broadcast URLs')
      this.broadcastObject.broadcastUploadProgress(0)
      this.broadcastObject.broadcastURLs(this.URLs)
    }
  }*/
}