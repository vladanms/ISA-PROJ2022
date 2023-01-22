import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Penal } from '../model/penal';
import { PenalsService } from '../services/penals.service';

@Component({
  selector: 'app-penals',
  templateUrl: './penals.component.html',
  styleUrls: ['./penals.component.css']
})
export class PenalsComponent implements OnInit {
  public dataSource = new MatTableDataSource<Penal>();
  public displayedColumns = ['date'];

  constructor(private penalsService: PenalsService) { }

  ngOnInit(): void {
    this.penalsService.getPenals(localStorage.getItem('loggedUser')).subscribe(res => {
      this.dataSource.data = res;
    })
  }

}
