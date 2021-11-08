import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IUser } from 'src/app/model/user';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
 
  userResults!: Array<IUser>;
  
  constructor(private route: ActivatedRoute,
              private userService: UserService) { }

  ngOnInit(): void {
    let sub = this.route.params.subscribe(params => {
      const input = params['input'];
      if (input != null) {
        this.userService.getUserListRequest(input).subscribe(data => {
          this.userResults = data;
        })
      }
    });
    
  }


}
