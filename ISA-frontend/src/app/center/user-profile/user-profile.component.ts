import { Component, OnInit } from '@angular/core';
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
  
  constructor(private userProfileService: UserProfileService) { }

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

  save(){}
  cancel(){}
}
