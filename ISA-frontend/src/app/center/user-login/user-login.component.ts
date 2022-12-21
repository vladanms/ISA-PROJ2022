import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UserLoginService } from '../services/user-login.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

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
            localStorage.setItem('loggedUserRole', 'Registered');
            window.location.href = '/centers';
            this.showSuccess();
          },
          error: (e) => {this.showError(e.error.Message, e.error.Title);
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

  register(){
    this.router.navigate(['/user-registration']);
  }
}
