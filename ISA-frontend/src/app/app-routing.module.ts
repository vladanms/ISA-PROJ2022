import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserRegistrationComponent } from './center/user-registration/user-registration.component';
import { CenterSearchComponent } from './center/center-search/center-search.component';
import { UserProfileComponent } from './center/user-profile/user-profile.component';
import { UserLoginComponent } from './center/user-login/user-login.component';
import { CentersComponent } from './center/centers/centers.component';
import { PersonalFileComponent } from './center/personal-file/personal-file.component';
import { AppointmentsFreeComponent } from './center/appointments-free/appointments-free.component';
import { AppointmentsScheduledComponent } from './center/appointments-scheduled/appointments-scheduled.component';
import { AppointmentsCreateComponent } from './center/appointment-create/appointments-create.component';


const routes: Routes = [
  { path: 'user-registration', component: UserRegistrationComponent },
  { path: 'center-search', component: CenterSearchComponent},
  { path: 'user-profile', component: UserProfileComponent},
  { path: 'user-login', component: UserLoginComponent },
  { path: 'centers', component: CentersComponent },
  { path: 'personal-file', component: PersonalFileComponent },
  { path: 'appointments-free', component:AppointmentsFreeComponent},
  { path: 'appointments-scheduled', component: AppointmentsScheduledComponent},
  { path: 'appointments-create', component: AppointmentsCreateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
