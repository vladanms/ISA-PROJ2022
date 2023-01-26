import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonalFileService } from '../services/personal-file.service';
import { ToastrService } from 'ngx-toastr';
import { Center } from '../model/center'
import { User } from '../model/user'
import { Time } from '@angular/common';
import { AppointmentsCreateService } from '../services/appointments-create.service';
import { CentersService } from '../services/centers.service';

@Component({
    selector: 'app-appointments-create',
    templateUrl: './appointments-create.component.html',
    styleUrls: ['./appointments-create.component.css']
  })

  export class AppointmentsCreateComponent implements OnInit{
    date : Date = null;
    time : Time = null;
    centerName: string = null;
    center: Center = null;
    
    constructor(private appointmentsCreateService: AppointmentsCreateService, private centersService: CentersService,
      private router: Router, private toastr: ToastrService) { }

    submit(){
      this.appointmentsCreateService.createNewAppointment(this. center, this.date, this.time).subscribe({
          next: (res) => {
              this.router.navigate(['center-home']);
              this.showSuccess();
            },
            error: (e) => {this.showError(e.error.Message, e.error.Title);
              console.log(e);}
      });
  }
  showSuccess() {
      this.toastr.success('success');
    }
    showError(message: string, title: string) {
      this.toastr.error(message, title);
    }

    ngOnInit(): void {
      this.centersService.getCenterByAdmin().subscribe(
          {
              next: (res) =>{
                  this.center = res;
              }
          }
      );
    }
  }