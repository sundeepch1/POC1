import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateUserComponent } from './user/create-user/create-user.component';
import { UserInfoComponent } from './user/user-info/user-info.component';
import { ViewUserComponent } from './user/view-user/view-user.component';

const routes: Routes = [
  {
    path:'',
    component: UserInfoComponent
  },
  {
    path: 'home',
    component: UserInfoComponent
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
    path:'',
    redirectTo:'home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
