import { Component } from '@angular/core';
import { Router, NavigationEnd, Event, ActivatedRoute } from '@angular/router';
import { CommonService } from 'src/app/common/common.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'task1-frontend';
  public routerOutlet:boolean = false;

  constructor(private commonService: CommonService, private router: Router) { }

  ngOnInit(){

    this.router.events.subscribe((event: Event) => {


      if (event instanceof NavigationEnd) {

        this.commonService.isLoggedIn().subscribe(result =>{
          this.routerOutlet = result;
          console.log('55555555555555555', result);
        });
      }
    });
  }
}
