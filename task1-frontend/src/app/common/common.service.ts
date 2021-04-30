import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor(private router: Router) { }

  getLoggedInUser(): any{
    if(localStorage.getItem('currentUser')){
      let loginuser = JSON.parse(localStorage.getItem('currentUser') || '{}');
      return loginuser.user;
    }else{
      this.router.navigate(['/login']);
      return false;
    }
  }

  isLoggedIn(): Observable<boolean>{
    if(localStorage.getItem('currentUser')){
      return of(true)
    }else{
      return of(false);
    }
  }

  getCurrentUserRole(){
    let user = this.getLoggedInUser();
    if(user){
      return user.role;
    }else{
      this.router.navigate(['/login']);
      return false;
    }
  }
}