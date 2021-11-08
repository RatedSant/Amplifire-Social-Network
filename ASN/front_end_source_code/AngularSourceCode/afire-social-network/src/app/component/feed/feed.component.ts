import { Component, Input, OnInit } from '@angular/core';
import { IPost } from 'src/app/model/post';
import { IUser } from 'src/app/model/user';
import { LikeService } from 'src/app/service/like.service';
import { LoginService } from 'src/app/service/login.service';
import { PostService } from 'src/app/service/post.service';
import { BroadcastObjectService } from 'src/app/shared/broadcast-object.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  @Input() post!: IPost;
  postComment: string
  showCommentTextArea: Boolean = false;
  comments: any
  currentUser?: IUser
  showExtendedDescription: Boolean = false;
  hasLiked: boolean = false;
  @Input() likeCount: number = 0;

  constructor(
    private postService: PostService,
    private broadcastObjectService: BroadcastObjectService,
    private loginService: LoginService,
    private likeService: LikeService,

  ) {
    this.postComment = ""
  }

  ngOnInit(): void {
    this.broadcastObjectService.currentUser.subscribe((user) => {
      this.currentUser = user
    })
    if (this.isUserLoggedIn()) {
      this.likeService.hasLike(this.currentUser?.asnUserId!, this.post.asnPostId!).subscribe(data => {
        console.log(data.result);
        this.hasLiked = data.result;
      });
    }
    this.likeService.getCount(this.post.asnPostId!).subscribe(data => {
      this.likeCount = data.count;
    });

  }

  hasVideo(): Boolean {
    if (this.post.asnPostDescription.includes('https://www.youtube.com/watch?v=')) {
      return true;
    }
    return false;
  }

  //https://www.youtube.com/watch?v=GYAB4Td62zI
  getYouTubeId(): string {
    const index = this.post.asnPostDescription.indexOf("watch?v=")
    //console.log('video ID')
    //console.log(this.post.asnPostDescription.substr(index + 8, index + 19))
    return this.post.asnPostDescription.substr(index + 8, index + 19)
  }


  isExtended(): Boolean {
    return this.showExtendedDescription
  }

  updateIsExtended() {
    this.showExtendedDescription = !this.showExtendedDescription
  }

  isUserLoggedIn(): Boolean {
    return this.loginService.getIsUserLoggedIn()
  }

  isUserPost(): Boolean {
    return this.post.asnUserFK.asnUserId == this.currentUser?.asnUserId
  }


  showCommentTA() {
    this.showCommentTextArea = !this.showCommentTextArea;

    if (this.showCommentTextArea) {
      this.postService.getCommentByPostId(this.post.asnPostId!).subscribe(comments => {

        //console.log(comments)
        this.comments = comments

      })
    }

  }

  saveComment() {
    this.postService.saveCommentRequest(this.postComment, this.post, this.currentUser!).subscribe(data => {
      //now get comments
      this.postService.getCommentByPostId(this.post.asnPostId!).subscribe(comments => {
        this.comments = comments
      })
    })
  }


  toggleLike() {
    let increment = this.likeService.toggleLikeRequest(this.currentUser?.asnUserId!, this.post.asnPostId!, this.hasLiked!);
    this.hasLiked = !this.hasLiked;
    if (increment) {
      this.likeCount++;
    } else {
      this.likeCount--;
    }
  }


  parseFullDate(d: any) {
    d = Number(d);
    var tp = new Date(d);
    return this._monthNames[tp.getMonth() + 1] + " / " + tp.getDate() + " / " + " " + tp.getFullYear() + " at  " + tp.getHours() + ":" + tp.getMinutes();
  }

  _monthNames = ['', 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

}