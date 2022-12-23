import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { appointmentDTO } from '../dto/appointmentDTO';

@Injectable({
  providedIn: 'root'
})
export class AppointmentsScheduledService {

  apiHost: string = 'http://localhost:8091/';
    headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(private http: HttpClient) { }

  getAppointments(user:any): Observable<appointmentDTO[]> {
    return this.http.get<appointmentDTO[]>(this.apiHost + 'appointment/getScheduledAppointments?user=' + user, {headers: this.headers});
  }

  cancelFreeAppointment(appointmentId:string, email:string):Observable<any> {
    let scheduleDTO = {
      appointmentId: appointmentId,
      email: email,
      centerId: ""
    }
    return this.http.put<any>(this.apiHost + 'appointment/cancelFreeAppointment', scheduleDTO, {headers: this.headers});
  }
}
