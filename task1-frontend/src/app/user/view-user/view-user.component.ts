import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { ToastrService } from 'ngx-toastr'; 

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.css']
})
export class ViewUserComponent implements OnInit {
  userId : number =0;
  userInfo: any;
  constructor(private route: ActivatedRoute, private userService: UserService, private  toastrService: ToastrService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params =>{
      this.userId = +params['userId'];
    });
    this.getUserById(this.userId);
  }

  getUserById(userId:number){

    this.userService.getUserById(userId).subscribe((user: any) =>{
      this.userInfo = user;
    }, error => {
      this.toastrService.error('Try later');
    })
}

}
