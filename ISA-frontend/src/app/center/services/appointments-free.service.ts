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

  getAppointments(email:string, centerId:any): Observable<appointmentDTO[]> {
    let scheduleDTO = {
      appointmentId: "",
      email: email,
      centerId: centerId
    }
    return this.http.put<appointmentDTO[]>(this.apiHost + 'appointment/getFreeAppointments', scheduleDTO, {headers: this.headers});
  }

  scheduleFreeAppointment(appointmentId:string, email:string):Observable<any> {
    let scheduleDTO = {
      appointmentId: appointmentId,
      email: email,
      centerId: ""
    }
    return this.http.put<any>(this.apiHost + 'appointment/scheduleFreeAppointment', scheduleDTO, {headers: this.headers});
  }
}
