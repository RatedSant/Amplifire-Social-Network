import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PostService } from 'src/app/service/post.service';
import { BroadcastObjectService } from 'src/app/shared/broadcast-object.service';
import { UploadImageService } from 'src/app/shared/upload-image.service';
import { IUser } from 'src/app/model/user';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  selectedFiles!: FileList;
  postDescription: string
  currentUser!: IUser
  uploadProgress: number
  URLs: any

  selectedImageUrl: any

  constructor(
    private postService: PostService,
    private broadcastObject: BroadcastObjectService,
    private uploadImageService: UploadImageService,
    private router: Router
  ) {
    this.postDescription = ""
    this.uploadProgress = 0
  }

  ngOnInit(): void {
    this.broadcastObject.currentUser.subscribe(
      user => {
        this.currentUser = user
      }
    )
    this.broadcastObject.currentUpload.subscribe(
      progress => {
        this.uploadProgress = progress
      }
    )
   /* this.broadcastObject.currentURLs.subscribe(data => {
      this.URLs = data
      console.log('received URLs')
      console.log(this.URLs)

      //dummy test
      if (this.URLs.length > 0) {
        this.postService.savePostRequest(this.postDescription, this.currentUser, this.URLs.toString()).subscribe(
          data => {
            console.log(data)
            this.broadcastObject.broadcastURLs([])
            this.router.navigate(['news-feed'])
          }
        )
      }
    })*/
  }

  detectFiles(event: any) {
    this.selectedFiles = event.target.files;
    //this part to display the selected image
    if (event.target.files && event.target.files[0]) {
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onloadend = (event) => {
        this.selectedImageUrl = event.target?.result;        
      };
    }
  }

  savePost() {
    //has image
    if (this.selectedFiles != undefined && this.selectedFiles.length > 0) {
      this.uploadImageService.uploadFile(this.selectedFiles).then( url => {
        this.postService.savePostRequest(this.postDescription, this.currentUser, url).subscribe(
          data => {
            this.router.navigate(['news-feed'])
          }
        )
      })
    }
    //only post description + currentUser
    else {
      this.postService.savePostRequest(this.postDescription, this.currentUser, '').subscribe(
        data => {
          this.router.navigate(['news-feed'])
        }
      )
    }
  }  
}