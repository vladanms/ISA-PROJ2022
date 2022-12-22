import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { appointmentDTO } from '../dto/appointmentDTO';

@Injectable({
  providedIn: 'root'
})
export class AppointmentsFreeService {

  apiHost: string = 'http://localhost:8091/';
    headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(private http: HttpClient) { }

  getAppointments(center:any): Observable<appointmentDTO[]> {
    return this.http.get<appointmentDTO[]>(this.apiHost + 'appointment/getFreeAppointments?center=' + center, {headers: this.headers});
  }
}
