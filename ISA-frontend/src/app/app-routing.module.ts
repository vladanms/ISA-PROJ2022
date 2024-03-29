import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserRegistrationComponent } from './center/user-registration/user-registration.component';
import { CenterSearchComponent } from './center/center-search/center-search.component';
import { UserProfileComponent } from './center/user-profile/user-profile.component';
import { UserProfileDisplayComponent } from './center/user-profile-display/user-profile-display.component';
import { UserLoginComponent } from './center/user-login/user-login.component';
import { CentersComponent } from './center/centers/centers.component';
import { PersonalFileComponent } from './center/personal-file/personal-file.component';
import { AppointmentsFreeComponent } from './center/appointments-free/appointments-free.component';
import { AppointmentsScheduledComponent } from './center/appointments-scheduled/appointments-scheduled.component';
import { AppointmentsCreateComponent } from './center/appointment-create/appointments-create.component';
import { AppointmentsAttendedComponent } from './center/appointments-attended/appointments-attended.component';
import { PenalsComponent } from './center/penals/penals.component';
import { AppointmentMakeComponent } from './center/appointment-make/appointment-make.component';
import { CenterHomeComponent } from './center/center-home/center-home.component';
import { AdminLoginComponent } from './center/admin-login/admin-login.component';

const routes: Routes = [
  { path: 'user-registration', component: UserRegistrationComponent },
  { path: 'center-search', component: CenterSearchComponent},
  { path: 'user-profile', component: UserProfileComponent},
  { path: 'user-login', component: UserLoginComponent },
  { path: 'admin-login', component: AdminLoginComponent },
  { path: 'centers', component: CentersComponent },
  { path: 'personal-file', component: PersonalFileComponent },
  { path: 'appointments-free', component:AppointmentsFreeComponent},
  { path: 'appointments-scheduled', component: AppointmentsScheduledComponent},
  { path: 'appointments-create', component: AppointmentsCreateComponent},
  { path: 'appointments-attended', component: AppointmentsAttendedComponent},
  { path: 'penals', component:PenalsComponent },
  { path: 'user-profile-display', component:UserProfileDisplayComponent},
  { path: 'appointment-make', component:AppointmentMakeComponent},
  { path: 'center-home', component:CenterHomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
