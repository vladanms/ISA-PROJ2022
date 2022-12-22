import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserRegistrationComponent } from './center/user-registration/user-registration.component';
import { CenterSearchComponent } from './center/center-search/center-search.component';
import { UserProfileComponent } from './center/user-profile/user-profile.component';
import { UserLoginComponent } from './center/user-login/user-login.component';
import { CentersComponent } from './center/centers/centers.component';
import { PersonalFileComponent } from './center/personal-file/personal-file.component';

const routes: Routes = [
  { path: 'user-registration', component: UserRegistrationComponent },
  { path: 'center-search', component: CenterSearchComponent},
  { path: 'user-profile', component: UserProfileComponent},
  { path: 'user-login', component: UserLoginComponent },
  { path: 'centers', component: CentersComponent },
  { path: 'personal-file', component: PersonalFileComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
