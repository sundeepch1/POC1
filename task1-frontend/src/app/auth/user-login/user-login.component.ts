import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../auth-service.service';
import { Router } from '@angular/router';
import { NgxPermissionsService } from 'ngx-permissions';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  public signinForm: any;
  constructor(private authService: AuthServiceService, private router: Router) { }

  ngOnInit() {
    this.signinForm = new FormGroup({
      emailAccount: new FormControl('',[
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/)
      ]),
      password: new FormControl('',[
        Validators.required,

      ])
    })
  }

  signin(){
    let signinData = this.signinForm.getRawValue();
    this.authService.loginUser(signinData).subscribe((response)=>{
      if(response.token){
        localStorage.setItem('currentUser', JSON.stringify(response));
        if(response.user.role.toUpperCase() == 'ADMIN' || response.user.role == 'ADMIN'){
          this.router.navigate(['/home']);
        }else{
          this.router.navigate(['/viewUser', response.user.id]);
        }
      }
    })
  }
}