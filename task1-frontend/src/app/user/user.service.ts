import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  getAllUsers(): Observable <any>{
    return this.http.get('http://localhost:9090/api/allUsers');
  }

  getUserByDOBJoiningDateOnSort(): Observable <any>{
    return this.http.get('http://localhost:9090/api/getUserByDOBJoiningDateOnSort');
  }
  
  deleteUserById(userId:any): Observable <any>{
    return this.http.delete('http://localhost:9090/api/hardDelete/' + userId)
  }

  enableDisableUserConfirm(userId:any): Observable <any>{
    return this.http.delete('http://localhost:9090/api/softDelete/' + userId)
  }

  getUserById(userId:number):Observable<any>{
    return this.http.get('http://localhost:9090/api/getUserById/'+userId);
  }

  saveUser(user: any):Observable<any>{
    return this.http.post('http://localhost:9090/api/saveUser', user);
  }

  getUserByNameSurnamePinCode(serachUser: any):Observable<any>{
    return this.http.post('http://localhost:9090/api/getUserByNameSurnamePinCode', serachUser);
  }
}
