import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Time } from '@angular/common';
import { CentersService } from './centers.service';
import { Center } from '../model/center';

@Injectable({
  providedIn: 'root'
})

export class AppointmentsCreateService {
    apiHost: string = 'http://localhost:8091/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  

    constructor(private http: HttpClient, private centersService: CentersService) { }

    createNewAppointment(center: Center, date: Date, time: Time): Observable<any>{
        let appointment = {
            center : center,
            date : date,
            time : time,
            user : null
        };

        
        return this.http.put<any>(this.apiHost + 'appointment/create', appointment, {headers: this.headers});
    }


}