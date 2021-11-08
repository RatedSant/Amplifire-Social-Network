import { Component, Input, OnInit } from '@angular/core';
import { IPost } from 'src/app/model/post';
import { IUser } from 'src/app/model/user';
import { BroadcastObjectService } from 'src/app/shared/broadcast-object.service';
import { PostService } from 'src/app/service/post.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ContentObserver } from '@angular/cdk/observers';
import { RegisterService } from 'src/app/service/register.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  currentUser!: IUser;
  userID!: number;
  givenUser!: IUser;

  constructor(
    private broadcastObjectService: BroadcastObjectService,
    private postService: PostService,
    private route: ActivatedRoute,
    private registerService: RegisterService,
    private redirect: Router

  ) { }

  ngOnInit(): void {
    this.broadcastObjectService.currentUser.subscribe(user => {
      this.currentUser = user
    })
    



    // let givenId = this.route.snapshot.paramMap.get('id')
    // this.userID = Number(givenId);

    let sub = this.route.params.subscribe(params =>{
      const givenId = params['id'];
      this.registerService.getUserById(givenId).subscribe(
        data =>{
        if(data === null){
          this.redirect.navigate(['news-feed']);
          setTimeout(() => {
            alert('This previous profile does not exist');
          }, 3000);
          
        }else{
          this.givenUser = data;
        }
      })

    })
  }

  navigateEdit(){
    this.redirect.navigate(['edit-profile']);
  }

  navigateCreate(){
    this.redirect.navigate(['create-post']);
  }

}
