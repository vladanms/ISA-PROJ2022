import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserRegistrationService } from '../services/user-registration.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {
  name: string = "";
  surname: string = "";
  email: string = "";
  password: string = "";
  confirmPassword: string = "";
  jmbg: string = "";
  address: string = "";
  city: string = "";
  country: string = "";
  phone: string = "";
  gender: Number = 0;
  occupation: string = "";
  company: string = "";

  constructor(private userRegistrationService: UserRegistrationService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
  }

  submit(){
    this.userRegistrationService.register(this.name, this.surname, this.email, this.password, this.jmbg,
      this.address, this.city, this.country, this.phone, this.gender, this.occupation, this.company).subscribe(
      {
        next: (res) => {
          this.router.navigate(['']);
          this.showSuccess();
        },
        error: (e) => {this.showError(e.error.Message, e.error.Title);
          console.log(e);}
    });
  }
  showSuccess() {
    this.toastr.success('User Registered', 'Clinic application');
  }
  showError(message: string, title: string) {
    this.toastr.error(message, title);
  }
}
