import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { appointmentDTO } from '../dto/appointmentDTO';
import { AppointmentsScheduledService } from '../services/appointments-scheduled.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-appointments-scheduled',
  templateUrl: './appointments-scheduled.component.html',
  styleUrls: ['./appointments-scheduled.component.css']
})
export class AppointmentsScheduledComponent implements OnInit {

  public dataSource = new MatTableDataSource<appointmentDTO>();
  public displayedColumns = ['centerName', 'date', 'time', 'cancel'];
  public appointments: appointmentDTO[] = [];

  constructor(private router:Router, private toastr: ToastrService, private appointmentsScheduledService: AppointmentsScheduledService) { }

  ngOnInit(): void {
    this.appointmentsScheduledService.getAppointments(localStorage.getItem('loggedUser')).subscribe(res => {
      this.appointments = res;
      this.dataSource.data = this.appointments;
    })
  }
  
  public cancel(id:any){
    this.appointmentsScheduledService.cancelFreeAppointment(id, localStorage.getItem('loggedUser')).subscribe({
      next: (res) => {
        this.router.navigate(['centers']);
        this.toastr.success('Appointment Cancelled', 'Clinic application');
        },
        error: (e) => {this.toastr.error('Schedule error', 'Clinic application');
          console.log(e);}
    });
  }
}
