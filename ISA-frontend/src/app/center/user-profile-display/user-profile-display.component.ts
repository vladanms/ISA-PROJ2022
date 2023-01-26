import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { UserProfileService } from '../services/user-profile-service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-user-profile-display',
  templateUrl: './user-profile-display.component.html',
  styleUrls: ['./user-profile-display.component.css']
})
export class UserProfileDisplayComponent implements OnInit {

  id: any = "";
  jmbg: any = "";
  name: string = "";
  surname: string = "";
  address: string = "";
  city: string = "";
  country: string = "";
  phone: string = "";
  occupation: string = "";

  

  constructor(private router:Router, private toastr: ToastrService, private userProfileService: UserProfileService) { }

  ngOnInit(): void {
    this.userProfileService.getUser().subscribe(res => {
      this.id = res.id;
      this.jmbg = res.JMBG;
      this.name = res.name;
      this.surname = res.surname;
      this.address = res.address;
      this.city = res.city;
      this.country = res.country;
      this.phone = res.phone;
      this.occupation = res.occupation;
  })
}

public editProfile()
{
  this.router.navigate(['/user-profile']);
}

public editFile()
{
  this.router.navigate(['/personal-file']);
}

public makeAppointment()
{
  this.router.navigate(['/appointments-free']);
}

}
