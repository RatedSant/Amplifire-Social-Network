import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'src/app/service/post.service';

@Component({
  selector: 'app-user-post',
  templateUrl: './user-post.component.html',
  styleUrls: ['./user-post.component.css']
})
export class UserPostComponent implements OnInit {

  posts: any

  constructor(
    private route: ActivatedRoute,
    private postService: PostService
  ) { }

  ngOnInit(): void {
    console.log('id is')
    console.log(this.route.snapshot.paramMap.get('id'))


    let sub = this.route.params.subscribe(params => {
      const givenId = params['id'];
    if (givenId != null) {
       this.postService.getPostByUserId(givenId).subscribe(data => {
       this.posts = data
       })
      }

    })



  }
}