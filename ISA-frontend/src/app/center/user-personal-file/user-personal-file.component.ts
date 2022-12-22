import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { personalFileService } from '../services/personal-file.service';
import { ToastrService } from 'ngx-toastr';

@Component({
    selector: 'app-user-personal-file',
    templateUrl: './user-personal-file.component.html',
    styleUrls: ['./user-personal-file.component.css']
})
export class UserPersonalFileComponent implements OnInit {
    name: string = "";
    jmbg: string = "";
    address: string = "";
    phone: string = "";
    company: string = "";
    occupation: string = "";
    previousDonations: number = 0;
    alreadyDonated: boolean = false;
    donationRefused: boolean = false;
    dangerousOccupation: boolean = false;
    prescriptionMeds: boolean = false;
    allergies: boolean = false;
    chronicIllness: boolean = false;
    termsAndConditions: boolean = false; 

    constructor(private userPersonalFileService: UserPersonalFileService, private router: Router, private toastr: ToastrService) { }

    ngOnInit(): void {
    }

    submit(){
        this.userPersonalFileService.fillPersonalFile(this.name, this.jmbg, this.address, this.phone,
            this.company, this.occupation, this.previousDonations, this.alreadyDonated, this.donationRefused,
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