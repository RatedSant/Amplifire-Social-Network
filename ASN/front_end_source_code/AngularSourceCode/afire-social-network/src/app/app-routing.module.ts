import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CreatePostComponent } from './component/create-post/create-post.component';
import { EditProfileComponent } from './component/edit-profile/edit-profile.component';
import { LoginComponent } from './component/login/login.component';
import { NewsFeedComponent } from './component/news-feed/news-feed.component';
import { ProfileComponent } from './component/profile/profile.component';
import { RegisterComponent } from './component/register/register.component';
import { UserPostComponent } from './component/user-post/user-post.component';
import { SearchComponent } from './component/search/search.component';

import { AuthGuardService } from './service/auth-guard.service';
import { ChatRoomComponent } from './component/chat-room/chat-room.component';

const routes: Routes = [  
  {path : '', component : NewsFeedComponent},
  {path : 'login', component : LoginComponent,},
  {path : 'create-post', component : CreatePostComponent, canActivate : [AuthGuardService] },    
  {path : 'news-feed', component : NewsFeedComponent},
  {path : 'register', component : RegisterComponent},
  {path : 'user-post/:id', component : UserPostComponent, canActivate : [AuthGuardService] },
  {path : 'edit-profile', component : EditProfileComponent, canActivate : [AuthGuardService] },
  {path : 'profile/:id', component : ProfileComponent},
  {path : 'search/:input', component : SearchComponent},   
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }