import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserLoginService } from '../services/user-login.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  email: string = "";
  password: string = "";

  constructor(private userLoginService: UserLoginService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
    localStorage.setItem('loggedUserRole', '');
  }

  login(){
    if(this.email == "" || this.password == ""){ 
      this.showError('Fill out all fields', 'Clinic application')
    } else {
      this.userLoginService.login(this.email, this.password).subscribe(
        {
          next: (res) => {
            localStorage.setItem('loggedUser', this.email);
            localStorage.setItem('loggedUserRole', 'Admin');
            window.location.href = '/center-home';
            this.showSuccess();
          },
          error: (e) => {this.showError('Login Failed', 'Clinic application');
            console.log(e);}
      });
    }
  }
  showSuccess() {
    this.toastr.success('Logged In', 'Clinic application');
  }
  showError(message: string, title: string) {
    this.toastr.error(message, title);
  }

}
