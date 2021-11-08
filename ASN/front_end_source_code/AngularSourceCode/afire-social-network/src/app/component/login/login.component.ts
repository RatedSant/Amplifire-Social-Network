import { Component, HostListener, Inject, Injectable, OnInit } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import { LoginService } from 'src/app/service/login.service';
import { Router } from '@angular/router';
import { BroadcastObjectService } from 'src/app/shared/broadcast-object.service';
import { UserService } from 'src/app/service/user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @HostListener('document:keyup.enter', ['$event']) onKeyupHandler(event: KeyboardEvent) {
    this.login();
  }
  email = new FormControl('', [Validators.required, Validators.email]);
  hide = true;
  recoverEmail!: string
  showForgotTextArea: Boolean = false;

  password: string;

  

  constructor(
    private loginService: LoginService,
    private router: Router,
    private broadcastObject: BroadcastObjectService,
    private userService: UserService,
    ) {
    this.password = ""
   }

  ngOnInit(): void {
  }


  showText() {
    this.showForgotTextArea = !this.showForgotTextArea;

    // if (this.showForgotTextArea) {
    //   this.postService.getCommentByPostId(this.post.asnPostId!).subscribe(comments => {

    //     //console.log(comments)
    //     this.comments = comments

    //   })
    // }

  }

  forgotPassword(){

    this.userService.recoverPassword(this.recoverEmail).subscribe(
      data => {
        if(data.status == 200){
          alert('Email sent with new password.');
        }else{
          alert('Wrong, Wrong, Wrong, Wrong, Wrong, Wrong, Wrong, Wrong')
        }
      }

    )

  }

  goToRegister(){

    this.router.navigate(['register']);

  }

  login(){    
    //let hash = Md5.hashStr(this.password);
    this.loginService.loginRequest(this.email.value, this.password).subscribe(
    //this.loginService.loginRequest(this.email.value, hash).subscribe(
      data => {
        console.log(data)
        if(data.asnUserId != 0){
          this.broadcastObject.broadcastUser(data)
          this.loginService.setIsUserLoggeIn(true)         
          this.router.navigate(['news-feed'])
        }
        else{
          alert("Check your credentials")
        }                
      }
    )
  }

  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }
    return this.email.hasError('email') ? 'Not a valid email' : '';
  }
}
