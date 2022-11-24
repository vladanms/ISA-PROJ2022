import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserRegistrationComponent } from './center/user-registration/user-registration.component';
import { UserProfileComponent } from './center/user-profile/user-profile.component';
import { CenterSearchComponent } from './center/center-search/center-search.component';

@NgModule({
  declarations: [
    AppComponent,
    UserRegistrationComponent,
    UserProfileComponent,
    CenterSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
