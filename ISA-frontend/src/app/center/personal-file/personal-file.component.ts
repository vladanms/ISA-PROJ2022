import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonalFileService } from '../services/personal-file.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-personal-file',
  templateUrl: './personal-file.component.html',
  styleUrls: ['./personal-file.component.css']
})
export class PersonalFileComponent implements OnInit {
  fullName: string = "";
  jmbg: string = "";
  address: string = "";
  phone: string = "";
  company: string = "";
  occupation: string = "";
  previousDonationsNo: number = 0;
  alreadyDonated: boolean = false;
  donationRefused: boolean = false;
  dangerousOccupation: boolean = false;
  prescriptionMeds: boolean = false;
  allergies: boolean = false;
  chronicIllness: boolean = false;
  termsAndConditions: boolean = false; 

  constructor(private personalFileService: PersonalFileService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
  }

  submit(){
      this.personalFileService.fillPersonalFile(this.fullName, this.jmbg, this.address, this.phone,
          this.company, this.occupation, this.previousDonationsNo, this.alreadyDonated, this.donationRefused,
          this.dangerousOccupation, this.prescriptionMeds, this.allergies, this.chronicIllness,
           this.termsAndConditions).subscribe({
          next: (res) => {
              this.router.navigate(['user-profile']);
              this.showSuccess();
            },
            error: (e) => {this.showError(e.error.Message, e.error.Title);
              console.log(e);}
      });
  }
  showSuccess() {
      this.toastr.success('success');
    }
    showError(message: string, title: string) {
      this.toastr.error(message, title);
    }
}
