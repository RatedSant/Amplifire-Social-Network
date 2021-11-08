import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/service/post.service';

@Component({
  selector: 'app-news-feed',
  templateUrl: './news-feed.component.html',
  styleUrls: ['./news-feed.component.css']
})
export class NewsFeedComponent implements OnInit {

  posts: any

  constructor(
    private postService: PostService    
    ) { }

  ngOnInit(): void {
    this.postService.getPostRequest().subscribe(data => {
      this.posts = data;
      console.log(this.posts)
    })
  } 
}