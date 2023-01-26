import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentMakeComponent } from './appointment-make.component';

describe('AppointmentMakeComponent', () => {
  let component: AppointmentMakeComponent;
  let fixture: ComponentFixture<AppointmentMakeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentMakeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentMakeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
