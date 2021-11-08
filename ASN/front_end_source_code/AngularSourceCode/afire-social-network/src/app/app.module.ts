//Custom Modules
import { LoginModule } from './component/login/login.module';
import { CreatePostModule } from './component/create-post/create-post.module';
import { NewsFeedModule } from './component/news-feed/news-feed.module';

//firestore
import { AngularFirestoreModule } from '@angular/fire/compat/firestore';
import { AngularFireModule } from '@angular/fire/compat';
import { environment } from '../environments/environment';

//youtube
import { YouTubePlayerModule } from "@angular/youtube-player";

import { NgModule, } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

//angular material
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatStepperModule} from '@angular/material/stepper';
import { MatListModule } from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card'

//components
import { RegisterComponent } from './component/register/register.component';
import { ProfileComponent } from './component/profile/profile.component';


import { UserPostComponent } from './component/user-post/user-post.component';
import { FeedComponent } from './component/feed/feed.component';
import { NewsFeedComponent } from './component/news-feed/news-feed.component';
import { MainNavComponent } from './component/main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';
import { EditProfileComponent } from './component/edit-profile/edit-profile.component';
import { SearchComponent } from './component/search/search.component';
import { ChatRoomComponent } from './component/chat-room/chat-room.component';




@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,      
    ProfileComponent,
    UserPostComponent,
    FeedComponent,
    NewsFeedComponent,
    MainNavComponent,
    EditProfileComponent,
    SearchComponent,
    ChatRoomComponent,
  ],
  imports: [
    //Custom Modules
    LoginModule,
    CreatePostModule,
    NewsFeedModule,

    MatListModule,
    MatStepperModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatIconModule,
    MatToolbarModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatCardModule,
    
    MatButtonModule,
    AppRoutingModule,
    BrowserAnimationsModule,   
    YouTubePlayerModule,
    AngularFirestoreModule,
    AngularFireModule.initializeApp(environment.firebase),
    LayoutModule,
  ],  
  bootstrap: [AppComponent],
  schemas: []
})
export class AppModule { }
