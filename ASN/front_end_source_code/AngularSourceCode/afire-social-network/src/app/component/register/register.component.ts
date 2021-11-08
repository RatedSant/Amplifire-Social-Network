import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CUser} from 'src/app/model/user';
import { RegisterService } from 'src/app/service/register.service';
import { ErrorStateMatcher } from '@angular/material/core';
import { UploadImageService } from 'src/app/shared/upload-image.service';
// import {FormBuilder, FormGroup, Validators} from '@angular/forms';

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}



@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user = new CUser('', '', '', '', '', '');
  username = new FormControl('', [Validators.required, Validators.email]);
  userEmail!: string;
  userFirstName!: string;
  userLastName!: string;
  userProfileURL!: string;
  userPassword!: string;

  selectedFiles: any;
  selectedImageUrl: string | ArrayBuffer | null | undefined;


  constructor(
    private registerService : RegisterService,
    private router: Router,
    private uploadImageService: UploadImageService
  ) { }

  ngOnInit(): void {
    
  }

  // createUser(){

  //   this.registerService.registerUser(this.username.value, this.userEmail, this.userFirstName,
  //     this.userLastName, this.userProfileURL, this.userPassword).subscribe(
  //       data => {
  //         if(data.asnUserFirstname == this.user.asnUserFirstname){
  //           alert("Account created from the website successfully!");
  //           this.router.navigate(['login']);
  //         }
  //         else{
  //           alert("Error try again.");
  //         }
  //       }
  //     )

  // }

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

  createUser(){

    this.uploadImageService.uploadFile(this.selectedFiles).then( url => {

      this.registerService.registerUser(this.user, url).subscribe(
        data => {
          console.log("data from server" + data);
          console.log("user input" + this.user);
          if(data.status == 200){
            alert("Account created from the website successfully!");
            this.router.navigate(['login']);
          }
          else{
            alert("The username is taken.");
          }
        }
        )
    })
  }

}





