import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserProfileServiceService {
  apiHost: string = 'http://localhost:8091/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getUser(): Observable<User>{
    return this.http.get<User>(this.apiHost + 'user/getUser?user=' + localStorage.getItem('loggedUser'),  {headers: this.headers});
  }
}
