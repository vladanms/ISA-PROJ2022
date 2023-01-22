import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { attendedAppointmentDTO } from '../dto/attendedAppointmentDTOView';
import { AppointmentsScheduledService } from '../services/appointments-scheduled.service';

@Component({
  selector: 'app-appointments-attended',
  templateUrl: './appointments-attended.component.html',
  styleUrls: ['./appointments-attended.component.css']
})
export class AppointmentsAttendedComponent implements OnInit {
  public dataSource = new MatTableDataSource<attendedAppointmentDTO>();
  public displayedColumns = ['centerName', 'date', 'time', 'price', 'length'];
  public appointments: attendedAppointmentDTO[] = [];

  constructor(private service: AppointmentsScheduledService) { }

  ngOnInit(): void {
    this.service.getAttendedAppointments(localStorage.getItem('loggedUser')).subscribe(res => {
      this.appointments = res;
      this.dataSource.data = this.appointments;
    })
  }

  public sortByCenter(){
    this.service.getAttendedAppointmentsByCenter(localStorage.getItem('loggedUser')).subscribe(res => {
      this.appointments = res;
      this.dataSource.data = this.appointments;
    })
  }
  public sortByPrice(){
    this.service.getAttendedAppointmentsByPrice(localStorage.getItem('loggedUser')).subscribe(res => {
      this.appointments = res;
      this.dataSource.data = this.appointments;
    })
  }
  public sortByLength(){
    this.service.getAttendedAppointmentsByLength(localStorage.getItem('loggedUser')).subscribe(res => {
      this.appointments = res;
      this.dataSource.data = this.appointments;
    })
  }

}
