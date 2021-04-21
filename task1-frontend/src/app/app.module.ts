import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {NgBootstrapFormValidationModule, CUSTOM_ERROR_MESSAGES} from 'ng-bootstrap-form-validation';
import { CUSTOM_ERRORS } from "./common/custom-errors";
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ToastrModule} from 'ngx-toastr';
import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import {TooltipModule} from 'ngx-bootstrap/tooltip';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { UserInfoComponent } from './user/user-info/user-info.component';
import {UserService} from './user/user.service';
import { CreateUserComponent } from './user/create-user/create-user.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    UserInfoComponent,
    CreateUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgBootstrapFormValidationModule.forRoot(),
    FormsModule,
    ReactiveFormsModule,
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
    {
      provide: CUSTOM_ERROR_MESSAGES,
      useValue: CUSTOM_ERRORS,
      multi: true
    },
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
