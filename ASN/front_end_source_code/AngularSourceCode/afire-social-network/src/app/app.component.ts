import { Component, OnInit, ViewChild } from '@angular/core';
import { LoginService } from './service/login.service';
import { Router } from '@angular/router';
import { BroadcastObjectService } from './shared/broadcast-object.service';

import { MatDrawer } from '@angular/material/sidenav';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {


  title = '🔥 Afire Socials Network 🔥';

  opened = false;
  user: any

  @ViewChild("drawer")
  public drawer: MatDrawer | undefined;

  constructor(
    private loginService: LoginService,
    private router: Router,
    private broadcastObject: BroadcastObjectService
  ) {

  }

  ngOnInit(): void {
    this.broadcastObject.currentUser.subscribe(user => {
      this.user = user;
    })
    this.loginService.userInfoRequest().subscribe(
      data => {
        console.log(data)
        if (data.asnUserId == 0) {
          this.loginService.setIsUserLoggeIn(false)
        }
        else{          
          this.broadcastObject.broadcastUser(data)
          this.loginService.setIsUserLoggeIn(true)
        }
      }
    )
  }

  login() {
    this.router.navigate(['login'])
    this.drawer?.toggle()
  }

  isLoggedIn(): boolean {
    return this.loginService.getIsUserLoggedIn();
  }

  logout() {
    this.loginService.logoutRequest().subscribe(
      data => {
        if (data.status == 200) {
          this.loginService.setIsUserLoggeIn(false)
          this.router.navigate(['login'])
          this.drawer?.toggle()
        }
      }
    )
  }
}
