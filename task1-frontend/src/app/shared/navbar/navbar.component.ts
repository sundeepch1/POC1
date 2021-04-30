import { Component, OnInit, Input } from '@angular/core';
import { AuthServiceService } from '../../auth/auth-service.service';
import { Router, NavigationEnd, Event } from '@angular/router';
import { CommonService } from '../../common/common.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Input() currentUserStatus:boolean = false;
  constructor(private router: Router, private authService: AuthServiceService, private commonService: CommonService) { }

  ngOnInit() {
    

    // this.router.events.subscribe((event: Event) => {


    //   if (event instanceof NavigationEnd) {

    //     this.commonService.isLoggedIn().subscribe(result =>{
    //       this.currentUserStatus = result;
    //       console.log('55555555555555555', result);
    //     });
    //   }
    // });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
