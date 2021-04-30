import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { AuthServiceService } from '../auth-service.service';
import * as moment from 'moment';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  public userForm: any;
  constructor(private toastrService: ToastrService, private authServiceService:AuthServiceService) { }

  ngOnInit() {
    
    this.userForm = new FormGroup({
      firstName: new FormControl('', [
        Validators.required
      ]),
      surName: new FormControl("", [
        Validators.required
      ]),
      emailAccount: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/)
      ]),
      phoneNumber: new FormControl('', [
        Validators.required
      ]),
      dateOfBirth: new FormControl('', [
        Validators.required
      ]),
      joiningDate: new FormControl('', [
        Validators.required
      ]),
      pinCode: new FormControl('', [
        Validators.required
      ]),
      lastCompanyPackage: new FormControl('', [
        Validators.required
      ]),
      lastCompanyName: new FormControl('', [
        Validators.required
      ]),
      currentCompanyPackage: new FormControl('', [
        Validators.required
      ]),
      currentCompanyName: new FormControl('', [
        Validators.required
      ]),
      relevantExperience: new FormControl('', [
        Validators.required
      ]),
      totalExperience: new FormControl('', [
        Validators.required
      ]),
      password: new FormControl('',
      [Validators.required])
    });

  }

  setUserForm(user:any){
    this.userForm.get('firstName').setValue(user.firstName);
    this.userForm.get('surName').setValue(user.surName);
    this.userForm.get('emailAccount').setValue(user.emailAccount);
    this.userForm.get('phoneNumber').setValue(user.phoneNumber);
    this.userForm.get('dateOfBirth').setValue(user.dateOfBirth);
    this.userForm.get('joiningDate').setValue(user.joiningDate);
    this.userForm.get('pinCode').setValue(user.pinCode);
    this.userForm.get('lastCompanyPackage').setValue(user.lastCompanyPackage);
    this.userForm.get('lastCompanyName').setValue(user.lastCompanyName);
    this.userForm.get('currentCompanyPackage').setValue(user.currentCompanyPackage);
    this.userForm.get('currentCompanyName').setValue(user.currentCompanyName);
    this.userForm.get('relevantExperience').setValue(user.relevantExperience);
    this.userForm.get('totalExperience').setValue(user.totalExperience);
    this.userForm.get('role').setValue(user.role);
  }

  saveUser(){
    let userData = this.userForm.getRawValue();
    let myMoment = moment(userData['dateOfBirth']).format('YYYY-MM-DD');
    userData['dateOfBirth'] = myMoment;
    myMoment = moment(userData['joiningDate']).format('YYYY-MM-DD');
    userData['joiningDate'] = myMoment;
    //userData['createdDate']= this.userInfo.createdDate;
    //userData['enabled'] = this.userInfo.enabled;
    this.authServiceService.saveUser(userData).subscribe(response =>{
      this.userForm.reset();
        this.toastrService.success(response.firstName + 'is created successfully.');
        //this.router.navigate(['/home'])
    },error => {
      this.toastrService.error('Try later');
    })
  }

}
