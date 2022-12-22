import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { centerDTO } from '../dto/centerDTO';
import { CentersService } from '../services/centers.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-centers',
  templateUrl: './centers.component.html',
  styleUrls: ['./centers.component.css']
})
export class CentersComponent implements OnInit {

  public dataSource = new MatTableDataSource<centerDTO>();
  public displayedColumns = ['name', 'address', 'description', 'grade', 'schedule'];
  public centers: centerDTO[] = [];

  constructor(private router:Router, private toastr: ToastrService, private centersService: CentersService) { }

  ngOnInit(): void {
    this.centersService.getCenters().subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
    })
  }

  public schedule(id: any){
    this.centersService.checkPersonalFile(localStorage.getItem('loggedUser')).subscribe(
      {next: (res) => {
        window.location.href = '';
        this.showSuccess();
      },
      error: (e) => {this.showError('Fill out your personal file first.', 'Clinic application');
        console.log(e);}
      }
    )
  }

  showSuccess() {
    this.toastr.success('success', 'Clinic application');
  }
  showError(message: string, title: string) {
    this.toastr.error(message, title);
  }
}
