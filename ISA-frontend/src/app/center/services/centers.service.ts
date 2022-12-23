import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { centerDTO } from '../dto/centerDTO';

@Injectable({
  providedIn: 'root'
})
export class CentersService {

  apiHost: string = 'http://localhost:8091/';
    headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(private http: HttpClient) { }

  getCenters(): Observable<centerDTO[]> {
    return this.http.get<centerDTO[]>(this.apiHost + 'center/getAllDTO', {headers: this.headers});
  }

  getCentersByName(): Observable<centerDTO[]> {
    return this.http.get<centerDTO[]>(this.apiHost + 'center/getAllDTOByName', {headers: this.headers});
  }

  getCentersByGrade(): Observable<centerDTO[]> {
    return this.http.get<centerDTO[]>(this.apiHost + 'center/getAllDTOByGrade', {headers: this.headers});
  }

  checkPersonalFile(email:string): Observable<any>{
    return this.http.post<any>(this.apiHost + 'user/checkPersonalFile', email, {headers: this.headers});
  }
}
