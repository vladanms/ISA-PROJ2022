import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  email: string = "";
  password: string = "";
  
  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  login(){
    
  }

  register(){
    this.router.navigate(['/user-registration']);
  }
}
