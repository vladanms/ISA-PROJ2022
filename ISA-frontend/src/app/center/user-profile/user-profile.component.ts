import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from '../model/user';
import { UserProfileService} from '../services/user-profile-service';

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
  occupation: string = "";
  user: User;
  
  constructor(private router:Router, private toastr: ToastrService, private userProfileService: UserProfileService) { }

  ngOnInit(): void {
    this.userProfileService.getUser().subscribe(res => {
      this.name = res.name;
      this.surname = res.surname;
      this.address = res.address;
      this.city = res.city;
      this.country = res.country;
      this.phone = res.phone;
      this.occupation = res.occupation;
    })
  }

  save()
  {
    this.user.name = this.name;
    this.user.surname = this.surname;
    this.user.surname = this.surname;
    this.user.address = this.address;
    this.user.city = this.city;
    this.user.country = this.country;
    this.user.phone = this.phone;
    this.user.occupation = this.occupation;
    this.userProfileService.saveUser(this.user);
    this.router.navigate(['/user-profile']);
  }

  cancel()
  {
    this.router.navigate(['/user-profile']);
  }
}
