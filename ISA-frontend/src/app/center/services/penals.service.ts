import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Penal } from '../model/penal';

@Injectable({
  providedIn: 'root'
})
export class PenalsService {

  apiHost: string = 'http://localhost:8091/';
    headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(private http: HttpClient) { }

  getPenals(id:any): Observable<Penal[]> {
    return this.http.get<Penal[]>(this.apiHost + 'user/getPenals?user=' + id, {headers: this.headers});
  }

}
