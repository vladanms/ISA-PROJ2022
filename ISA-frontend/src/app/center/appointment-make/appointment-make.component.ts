import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { centerDTO } from '../dto/centerDTO';
import { ToastrService } from 'ngx-toastr';
import { AppointmentsFreeService } from '../services/appointments-free.service';
import { CentersService } from '../services/centers.service';
import { Time } from '@angular/common';

@Component({
  selector: 'app-appointment-make',
  templateUrl: './appointment-make.component.html',
  styleUrls: ['./appointment-make.component.css']
})
export class AppointmentMakeComponent implements OnInit {

  date: Date = null;
  time: Time = null;

  public dataSource = new MatTableDataSource<centerDTO>();
  public displayedColumns = ['name', 'address', 'description', 'grade', 'schedule'];
  public centers: centerDTO[] = [];

  constructor(private router:Router, private toastr: ToastrService, private centersService: CentersService) { }

  ngOnInit(): void {
  }

  public Search(){}

  public SortByGrade(){
    this.centersService.getCentersByGrade().subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
  });
  }

  public schedule(id: any, name:any){
    this.centersService.checkPersonalFile(localStorage.getItem('loggedUser')).subscribe(
      {next: (res) => {
        localStorage.setItem('center', id);
        localStorage.setItem('centerName', name);
        window.location.href = 'appointments-free';
      },
      error: (e) => {this.showError('Fill out your personal file first.', 'Clinic application');
        console.log(e);}
      }
    )
  }

  showError(message: string, title: string) {
    this.toastr.error(message, title);
  }
}
