import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {
  apiHost: string = 'http://localhost:8091/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  register(name: string, surname: string, email: string, password: string, jmbg: string,
    address: string, city: string, country: string, phone: string, gender: Number, occupation: string,
    company: string): Observable<any> {
    let userDTO = {
      name: name,
      surname: surname,
      email: email,
      password: password,
      jmbg: jmbg,
      address: address,
      city: city,
      country: country,
      phone: phone,
      gender: gender,
      occupation: occupation,
      company: company
    };

    return this.http.post<any>(this.apiHost + 'user/register', userDTO, {headers: this.headers});
  }
}
