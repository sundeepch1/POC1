import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { UserService } from '../user.service';
import * as moment from 'moment';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  public userId: number = 0;
  public userForm: any;
  public userInfo: any;
  constructor(private toastrService: ToastrService,
              private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.params.subscribe(params =>{
      this.userId = +params['userId'];
    });

    if(!Number.isNaN(this.userId) && this.userId != 0){
      this.getUserById(this.userId);
    }

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
    });

  }

  getUserById(userId:number){

      this.userService.getUserById(userId).subscribe((user: any) =>{
        this.setUserForm(user)
        this.userInfo = user;
      }, error => {
        this.toastrService.error('Try later');
      })
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
  }

  saveUser(){
    let userData = this.userForm.getRawValue();
    if(!Number.isNaN(this.userId) && this.userId != 0){
      userData['id'] = this.userInfo.id;
    }
    let myMoment = moment(userData['dateOfBirth']).format('YYYY-MM-DD');
    userData['dateOfBirth'] = myMoment;
    myMoment = moment(userData['joiningDate']).format('YYYY-MM-DD');
    userData['joiningDate'] = myMoment;
    //userData['createdDate']= this.userInfo.createdDate;
    //userData['enabled'] = this.userInfo.enabled;
    this.userService.saveUser(userData).subscribe(response =>{
      this.userForm.reset();
        this.toastrService.success(response.firstName + 'is created successfully.');
        //this.router.navigate(['/home'])
    },error => {
      this.toastrService.error('Try later');
    })
  }

}
