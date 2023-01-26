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

  searchCenters(name: String, address: String, avgGrade: String): Observable<centerDTO[]> {
    if(name.length < 1)
    {name = 'isNull';}
    if(address.length < 1)
    {address = 'isNull';}
    if(avgGrade.length < 1)
    {avgGrade = 'isNull';}
    console.log(name, address, avgGrade);
    return this.http.get<centerDTO[]>(this.apiHost + 'center/getByCustomParameters?name=' + name + '?' + address + '?' + avgGrade,
     {headers: this.headers});
  }

  getCenterByAdmin(): Observable<centerDTO>
  {
    return this.http.get<centerDTO>(this.apiHost + 'center/getByAdmin?email=' + localStorage.getItem('loggedUser'), {headers: this.headers});
  }
}
