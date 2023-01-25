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

  name: string = "";
  address: string = "";
  grade: number = "";

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

  public sortByName(){
    this.centersService.getCentersByName().subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
    })
  }

  public sortByGrade(){
    this.centersService.getCentersByGrade().subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
    })
  }

  public Search()
  {
    this.centersService.searchCenters(this.name, this.address,this.grade).subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
    })
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
