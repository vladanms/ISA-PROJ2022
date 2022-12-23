import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { appointmentDTO } from '../dto/appointmentDTO';
import { AppointmentsFreeService } from '../services/appointments-free.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-appointments-free',
  templateUrl: './appointments-free.component.html',
  styleUrls: ['./appointments-free.component.css']
})
export class AppointmentsFreeComponent implements OnInit {

  public dataSource = new MatTableDataSource<appointmentDTO>();
  public displayedColumns = ['date', 'time', 'schedule'];
  public appointments: appointmentDTO[] = [];
  public centerName:string = "";

  constructor(private router:Router, private toastr: ToastrService, private appointmentsFreeService: AppointmentsFreeService) { }

  ngOnInit(): void {
    this.centerName = localStorage.getItem('centerName');
    this.appointmentsFreeService.getAppointments(localStorage.getItem('loggedUser'), localStorage.getItem('center')).subscribe(res => {
      this.appointments = res;
      this.dataSource.data = this.appointments;
    })
  }

  public schedule(id:any){
    this.appointmentsFreeService.scheduleFreeAppointment(id, localStorage.getItem('loggedUser')).subscribe({
      next: (res) => {
        this.router.navigate(['centers']);
        this.toastr.success('Appointment Scheduled', 'Clinic application');
        },
        error: (e) => {this.toastr.error('Schedule error', 'Clinic application');
          console.log(e);}
    });
  }
}
