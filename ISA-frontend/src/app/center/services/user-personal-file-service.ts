import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UerPersonalFileService {
    apiHost: string = 'http://localhost:8091/';
    headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  
    constructor(private http: HttpClient) { }
  
    fillPersonalFile(name: string, jmbg: string, address: string, phone: string, company: string, occupation: string;
        previousDonations: number, alreadyDonated: boolean, donationRefused: boolean, dangerousOccupation: boolean,
        prescriptionMeds: boolean, allergies: boolean, chronicIlness: boolean, termsAndConditions: boolean): Observable<any> {
      let personalFile = {
        name: name,
        jmbg: jmbg,
        address: address,
        phone: phone,
        company: company,
        occupation: occupation,
        previousDonations: previousDonations,
        alreadyDonated: alreadyDonated,
        donationRefused: donationRefused,
        dangerousOccupation: dangerousOccupation,
        prescriptionMeds: prescriptionMeds,
        allergies: allergies,
        chronicIlness: chronicIlness,
        termsAndConditions: termsAndConditions,
      };
  
      return this.http.post<any>(this.apiHost + 'user/fillPersonalFile', personalFile, {headers: this.headers});
    }
}