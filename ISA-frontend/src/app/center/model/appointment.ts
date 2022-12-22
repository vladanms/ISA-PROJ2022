import { Time } from "@angular/common";
import { User } from "./user";
import { Center } from "./center"

export class Appointment{
    center: Center;
    date: Date;
    time: Time;
    user: User;
}