import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  getAllUsers(): Observable <any>{
    //const headers = new HttpHeaders({'Access-Control-Allow-Origin':'*'});
    return this.http.get('/api/allUsers');//, {headers: headers});
  }

  getUserByDOBJoiningDateOnSort(): Observable <any>{
    return this.http.get('/api/getUserByDOBJoiningDateOnSort');
  }
  
  deleteUserById(userId:any): Observable <any>{
    return this.http.delete('/api/hardDelete/' + userId)
  }

  enableDisableUserConfirm(userId:any): Observable <any>{
    return this.http.delete('/api/softDelete/' + userId)
  }

  getUserById(userId:number):Observable<any>{
    return this.http.get('/api/getUserById/'+userId);
  }

  saveUser(user: any):Observable<any>{
    return this.http.post('/api/saveUser', user);
  }

  getUserByNameSurnamePinCode(serachUser: any):Observable<any>{
    return this.http.post('/api/getUserByNameSurnamePinCode', serachUser);
  }
}
