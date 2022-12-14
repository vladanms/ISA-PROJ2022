import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { centerDTO } from '../dto/centerDTO';

@Injectable({
  providedIn: 'root'
})
export class CenterSearchService {

  apiHost: string = 'http://localhost:8091/';
  headers: HttpHeaders = new HttpHeaders({
  'Content-Type': 'application/json',
});

  constructor(private http: HttpClient) { }

  getCenters(name: any, address: any, avgGrade: any): Observable<centerDTO[]> {
    return this.http.get<centerDTO[]>(this.apiHost + 'center/getByCustomParameters?name=' + name + '?address=' + address + '?avgGrade=' + avgGrade,
     {headers: this.headers});
  }

}
