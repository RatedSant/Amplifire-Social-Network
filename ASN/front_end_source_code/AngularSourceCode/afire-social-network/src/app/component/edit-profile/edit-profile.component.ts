import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CUser, IUser } from 'src/app/model/user';
import { RegisterService } from 'src/app/service/register.service';
import { BroadcastObjectService } from 'src/app/shared/broadcast-object.service';
import { UploadImageService } from 'src/app/shared/upload-image.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {


  currentUser!: IUser;
  selectedFiles: any;
  selectedImageUrl: string | ArrayBuffer | null | undefined;
  oldImage: any;

  constructor(
    private broadcastObjectService: BroadcastObjectService,
    private router: Router,
    private registerService: RegisterService,
    private uploadImageService: UploadImageService

    
  ) { }

  ngOnInit(): void {
    this.broadcastObjectService.currentUser.subscribe(user => {
      this.currentUser = user
      this.oldImage = this.currentUser.asnUserProfileImageUrl;
    })

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

  updateUser(){


    if(this.selectedImageUrl != undefined){

      this.uploadImageService.uploadFile(this.selectedFiles).then( url => {
        
        this.registerService.updateUser(this.currentUser, url).subscribe(
          data => {
            if(data.asnUser == this.currentUser.asnUser){
              alert("Yes it works buddy with new image!!!!");
              this.broadcastObjectService.broadcastUser(data);
              this.router.navigate(['news-feed']);
            }
            else{
              alert("This username is used by someone else.");
            }
          })
        })
      }else{

        this.registerService.updateUser(this.currentUser, this.oldImage).subscribe(
          data => {
            if(data.asnUser == this.currentUser.asnUser){
              alert("Yes it works buddy with old image!!!!");
              this.broadcastObjectService.broadcastUser(data);
              this.router.navigate(['news-feed']);
            }
            else{
              alert("This username is used by someone else.");
            }
          })
      }

    }
}
