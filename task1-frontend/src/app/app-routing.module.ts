import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserLoginComponent } from './auth/user-login/user-login.component';
import { UnknownPageComponent } from './common/unknown-page/unknown-page.component';
import { CreateUserComponent } from './user/create-user/create-user.component';
import { UserInfoComponent } from './user/user-info/user-info.component';
import { ViewUserComponent } from './user/view-user/view-user.component';
import { AuthGuardService } from './common/auth-guard.service';
import { RegisterUserComponent } from './auth/register-user/register-user.component';

const routes: Routes = [
  {
    path:'',
    component: UserLoginComponent
  },
  {
    path:'login',
    component: UserLoginComponent
  },
  {
    path:'signup',
    component: RegisterUserComponent
  },
  {
    path:'404',
    component: UnknownPageComponent
  },
  {
    path: 'home',
    component: UserInfoComponent,
    canActivate: [AuthGuardService]
  },
  {
    path:'createUser',
    component:CreateUserComponent
  },
  {
    path:'updateUser/:userId',
    component:CreateUserComponent
  }, 
  {
    path:'viewUser/:userId',
    component:ViewUserComponent
  }, 
  {
    path:'**',
    redirectTo:'404',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
