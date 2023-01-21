import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  name: string = "";
  surname: string = "";
  address: string = "";
  city: string = "";
  country: string = "";
  phone: string = "";
  gender: Number = 0;
  occupation: string = "";
  
  constructor() { }

  ngOnInit(): void {
  }

  save(){}
  cancel(){}
}
