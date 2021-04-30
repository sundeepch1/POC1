import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthGuardService } from './common/auth-guard.service';
import { NgBootstrapFormValidationModule, CUSTOM_ERROR_MESSAGES} from 'ng-bootstrap-form-validation';
import { CUSTOM_ERRORS } from "./common/custom-errors";
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ToastrModule} from 'ngx-toastr';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import {TooltipModule} from 'ngx-bootstrap/tooltip';
import {NgxPermissionsModule} from 'ngx-permissions';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { UserInfoComponent } from './user/user-info/user-info.component';
import {UserService} from './user/user.service';
import {HttpInterceptorService} from './common/http.interceptor.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { CreateUserComponent } from './user/create-user/create-user.component';
import { ViewUserComponent } from './user/view-user/view-user.component';
import { UserLoginComponent } from './auth/user-login/user-login.component';
import { UnknownPageComponent } from './common/unknown-page/unknown-page.component';
import { RegisterUserComponent } from './auth/register-user/register-user.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    UserInfoComponent,
    CreateUserComponent,
    ViewUserComponent,
    UserLoginComponent,
    UnknownPageComponent,
    RegisterUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgBootstrapFormValidationModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
    NgxPermissionsModule.forRoot({
    }),
    ToastrModule.forRoot(),
    // LaddaModule.forRoot({
    //   style: "zoom-in",
    //   spinnerSize: 20,
    //   spinnerColor: "white",
    //   spinnerLines: 12
    // }),
    BsDatepickerModule.forRoot(),
    TooltipModule.forRoot(),
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [
    AuthGuardService,
    {
      provide: CUSTOM_ERROR_MESSAGES,
      useValue: CUSTOM_ERRORS,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
