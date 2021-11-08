import { Component,  HostListener,Input } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { LoginService } from '../../service/login.service';
import { BroadcastObjectService } from 'src/app/shared/broadcast-object.service';
import { IUser } from 'src/app/model/user';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from 'src/app/service/post.service';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css']
})
export class MainNavComponent {
  
  title = '🔥 Afire Socials Network 🔥';
  posts: any;
  currentUser!: IUser;
  searchInput: String = "";


  

  @HostListener('document:keyup.enter', ['$event']) onKeyupHandler(event: KeyboardEvent) {
    this.runSearch();
  }

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  ngOnInit(): void {

    this.broadcastObjectService.currentUser.subscribe(user => {
      this.currentUser = user
    })

    let id = this.route.snapshot.paramMap.get('id')

    if (id != null) {
      this.postService.getPostByUserId(Number(id)).subscribe(data => {
        this.posts = data
      })
    }
  }

  runSearch(){
    if(this.searchInput){
      this.router.navigate(['search/'+this.searchInput]);
      this.searchInput="";
    }
  }

  getUserStatus() {
    return this.userStatus.getIsUserLoggedIn();
  }

  userLogout() {
    this.userStatus.logoutRequest().subscribe(
      data => {
        if (data.status == 200) {
          this.userStatus.setIsUserLoggeIn(false);
        }
      }
    );
    return this.userStatus.getIsUserLoggedIn();
  }

  redirectMain() {
    this.router.navigate(['news-feed'])
  }

  redirectProfile() {
    this.router.navigate(['profile', this.currentUser.asnUserId])
  }


  constructor(private breakpointObserver: BreakpointObserver,
    private userStatus: LoginService,
    private broadcastObjectService: BroadcastObjectService,
    private route: ActivatedRoute,
    private postService: PostService,
    private router: Router
  ) { }

}
