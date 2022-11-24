import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  public gender: Number = 0;
  constructor() { }

  ngOnInit(): void {
  }

  save(){}
  cancel(){}
}
