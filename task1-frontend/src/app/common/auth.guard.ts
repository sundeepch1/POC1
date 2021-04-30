import {Injectable} from '@angular/core';
import {Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import { CommonService } from './common.service';
import { NgxPermissionsService } from 'ngx-permissions';

@Injectable({
    providedIn: 'root'
})
export class AuthGuard implements CanActivate{

 constructor(private router : Router, private commonService: CommonService, private permissionsService: NgxPermissionsService){
 }

 canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean{
    if(this.isAuthenticated()){
        this.getUserRole();

        // if (route.data.roles && route.data.roles.indexOf(currentUser.role) === -1) {
        //     // role not authorised so redirect to home page
        //     this.router.navigate(['/']);
        //     return false;
        // }
        console.log('333333333333333333333');
        return true;
    }
    this.router.navigate(['/login']);
    return false;
 }

    isAuthenticated():boolean{
        let isAuthenticate: boolean = false;
        this.commonService.isLoggedIn().subscribe(result =>{
            isAuthenticate = result
        })
        return isAuthenticate;
    }

    getUserRole(){
        let role = this.commonService.getCurrentUserRole();

        console.log('333333333333333333333', role);
        if(role){
            const perm = [role];
            this.permissionsService.loadPermissions(perm);
        }
    }

}
