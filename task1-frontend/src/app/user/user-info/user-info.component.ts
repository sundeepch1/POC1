import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms';
declare var $: any;

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {
  public userList: any;
  public user: any= undefined;
  public searchUserForm: any;
  constructor(private userService: UserService, private toastrService: ToastrService, private router: Router) { }

  ngOnInit(): void {
    this.getAllUsers();

    this.searchUserForm = new FormGroup({
      firstName: new FormControl('', [
        Validators.required
      ]),
      surName: new FormControl("", [
        Validators.required
      ]),
      pinCode: new FormControl('', [
        Validators.required
      ])
    });
  }

  searchUser(){
    let searchUserData = this.searchUserForm.getRawValue();
    this.userList = undefined;
    this.userService.getUserByNameSurnamePinCode(searchUserData).subscribe(response =>{
      this.searchUserForm.reset();
      this.userList = response;
    },error => {
      this.toastrService.error('Try later');
    })
  }

  

  getAllUsers(){
    this.userList = undefined;
    this.userService.getAllUsers().subscribe(response => {
      this.userList = response;
    }, error =>{
      this.toastrService.error('Try later');
    })
  }

  enableDisableUser(id:string, user:any){
    this.user = user;
    $('#'+id).modal('show');
  }

  deleteUserById(id:string ,user:any){
    this.user = user;
    $('#'+id).modal('show');
  }

  enableDisableUserConfirm(id:string){
    $('#'+id).modal('hide');
    this.userService.enableDisableUserConfirm(this.user.id).subscribe(response =>{
      this.toastrService.success(this.user.firstName + ' is updated successfully.');
      this.getAllUsers();
    }, error =>{
      this.toastrService.error('Try later');
    })
  }

  deleteUserByIdConfirm(id:string){
    $('#'+id).modal('hide');
    this.userService.deleteUserById(this.user.id).subscribe(response =>{
      this.toastrService.success(this.user.firstName + ' is deleted successfully.');
      this.getAllUsers();
    }, error =>{
      this.toastrService.error('Try later');
    })
  }

  updateUser(user:any){
    this.user= user;
    this.router.navigate(['/updateUser', this.user.id]);
  }

  viewUser(user:any){
    this.user= user;
    this.router.navigate(['/viewUser', this.user.id]);
  }

  getUserByDOBJoiningDateOnSort(){
    this.userList = undefined;
    this.userService.getUserByDOBJoiningDateOnSort().subscribe(response => {
      this.userList = response;
    }, error =>{
      this.toastrService.error('Try later');
    })
  }

}
