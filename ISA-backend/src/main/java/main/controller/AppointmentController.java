package main.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import main.dto.AppointmentDTO;
import main.dto.AppointmentDTOView;
import main.dto.CenterDTOView;
import main.model.*;
import main.service.AppointmentService;
import main.service.CenterService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "appointment")
public class AppointmentController {

    User loggedInUser = new User();
    
    @Autowired
	private AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDTO appointmentDTO)
    {
        Appointment appointment = new Appointment
        (
        appointmentDTO.getCenter(),
        appointmentDTO.getDate(),
        appointmentDTO.getTime(),
        appointmentDTO.getUser()
        );

        if(appointmentService.AddAppointment(appointment)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/schedule")
    public ResponseEntity<Appointment> scheduleAppointment(@RequestBody AppointmentDTO appointmentDTO)
    {
        Appointment appointment = new Appointment
        (
        appointmentDTO.getCenter(),
        appointmentDTO.getDate(),
        appointmentDTO.getTime(),
        appointmentDTO.getUser()
        );

        if(appointmentService.schedule(appointment, loggedInUser) == 0)
        {
        return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/cancel")
    public ResponseEntity<Appointment> cancelAppointment(@RequestBody AppointmentDTO appointmentDTO)
    {
        Appointment appointment = new Appointment
        (
        appointmentDTO.getCenter(),
        appointmentDTO.getDate(),
        appointmentDTO.getTime(),
        appointmentDTO.getUser()
        );

        if(appointmentService.cancel(appointment) == true)
        {
        return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/getFreeAppointments")
    public @ResponseBody ArrayList<AppointmentDTOView> getFreeAppointments(@Param("center") String center){ 
		ArrayList<Appointment> appointments = appointmentService.getAll();
		ArrayList<AppointmentDTOView> appointmentDTOs = new ArrayList<AppointmentDTOView>();
		
		for(Appointment a : appointments){
			if(a.getCenter().getId().equals(Long.parseLong(center)) && a.getUser() == null) {
				AppointmentDTOView app = new AppointmentDTOView();
				app.setId(a.getId().intValue());
				app.setCenterName(a.getCenter().getName());
				app.setDate(a.getDate().toString());
				app.setTime(a.getTime().toString());
				appointmentDTOs.add(app);
			}
		}
		
		return appointmentDTOs;
	}
    
    @PostMapping("/getScheduledAppointments")
    public @ResponseBody ArrayList<AppointmentDTOView> getScheduledAppointments(@RequestBody String userEmail){ 
		ArrayList<Appointment> appointments = appointmentService.getAll();
		ArrayList<AppointmentDTOView> appointmentDTOs = new ArrayList<AppointmentDTOView>();
		
		for(Appointment a : appointments){
			if(a.getUser().getEmail().equals(userEmail)) {
				AppointmentDTOView app = new AppointmentDTOView();
				app.setId(a.getId().intValue());
				app.setCenterName(a.getCenter().getName());
				app.setDate(a.getDate().toString());
				app.setTime(a.getTime().toString());
				appointmentDTOs.add(app);
			}
		}
		
		return appointmentDTOs;
	}

}
