import { Component, OnInit } from '@angular/core';
import { CenterSearchService } from '../services/center-search.service';
import { CentersService } from '../services/centers.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { MatTableDataSource } from '@angular/material/table';
import { centerDTO } from '../dto/centerDTO';

@Component({
  selector: 'app-center-search',
  templateUrl: './center-search.component.html',
  styleUrls: ['./center-search.component.css']
})
export class CenterSearchComponent implements OnInit {

  public dataSource = new MatTableDataSource<centerDTO>();
  public displayedColumns = ['name', 'address', 'description', 'grade', 'schedule'];
  public centers: centerDTO[] = [];


  constructor(private router:Router, private toastr: ToastrService, private centerSearchService: CenterSearchService, private centerService: CentersService) { }

  name: string = "";
  address: string = "";
  avgGrade: number = 0;

  ngOnInit(): void {
    this.centerService.getCenters().subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
    })
  }

  submit(){

    this.centerSearchService.getCenters(this.name, this.address, this.avgGrade).subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
    });
  }

  reset()
  {
    this.centerService.getCenters().subscribe(res => {
      this.centers = res;
      this.dataSource.data = this.centers;
    });
  }

}
