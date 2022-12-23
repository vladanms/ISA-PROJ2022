package main.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;

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
import main.dto.FreeAppointmentScheduleDTO;
import main.model.*;
import main.service.AppointmentService;
import main.service.CenterService;
import main.service.RestrictedAppointmentService;
import main.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "appointment")
public class AppointmentController {

    User loggedInUser = new User();
    
    @Autowired
	private AppointmentService appointmentService;
    
    @Autowired
	private UserService userService;
    
    @Autowired
    private RestrictedAppointmentService restrictedAppointmentService;

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
    
    @PutMapping("/getFreeAppointments")
    public @ResponseBody ArrayList<AppointmentDTOView> getFreeAppointments(@RequestBody FreeAppointmentScheduleDTO fasDTO){ 
		ArrayList<Appointment> appointments = appointmentService.getAll();
		ArrayList<RestrictedAppointment> restricted = restrictedAppointmentService.getAll();
		ArrayList<AppointmentDTOView> appointmentDTOs = new ArrayList<AppointmentDTOView>();

		for(Appointment a : appointments){
			if(a.getCenter().getId().equals(Long.parseLong(fasDTO.getCenterId())) && a.getUser() == null) {
				AppointmentDTOView app = new AppointmentDTOView();
				app.setId(a.getId().toString());
				app.setCenterName(a.getCenter().getName());
				app.setDate(a.getDate().toString());
				app.setTime(a.getTime().toString());
				appointmentDTOs.add(app);
			}
		}
		
		if(restricted.isEmpty()) {
			return appointmentDTOs;
		}
		
		ArrayList<AppointmentDTOView> appointmentDTOs2 = new ArrayList<AppointmentDTOView>();
		for(AppointmentDTOView a : appointmentDTOs) {
			for(RestrictedAppointment ra : restricted) {
				if((ra.getAppointmentId().equals(a.getId())) && (ra.getEmail().equals(fasDTO.getEmail()))) {
					break;
				} else {
					appointmentDTOs2.add(a);
				}
			}
		}
		
		return appointmentDTOs2;
	}
    
    @PutMapping("/scheduleFreeAppointment")
    public ResponseEntity<Appointment> scheduleFreeAppointment(@RequestBody FreeAppointmentScheduleDTO fasDTO)
    {
    	Optional<Appointment> a = appointmentService.getById(Long.parseLong(fasDTO.getAppointmentId()));
    	if(a.get() == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	a.get().setUser(userService.findByEmail(fasDTO.getEmail()));
    	appointmentService.scheduleFreeAppointment(a.get());
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PutMapping("/cancelFreeAppointment")
    public ResponseEntity<Appointment> cancelFreeAppointment(@RequestBody FreeAppointmentScheduleDTO fasDTO)
    {
    	Optional<Appointment> a = appointmentService.getById(Long.parseLong(fasDTO.getAppointmentId()));
    	if(a.get() == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	a.get().setUser(null);
    	appointmentService.scheduleFreeAppointment(a.get());
    	RestrictedAppointment ra = new RestrictedAppointment();
    	ra.setAppointmentId(fasDTO.getAppointmentId());
    	ra.setEmail(fasDTO.getEmail());
    	restrictedAppointmentService.add(ra);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/getAll")
    public @ResponseBody ArrayList<Appointment> getAll(){ 
		return appointmentService.getAll();
    }
    
    @GetMapping("/getScheduledAppointments")
    public @ResponseBody ArrayList<AppointmentDTOView> getScheduledAppointments(@Param("user") String user){ 
		ArrayList<Appointment> appointments = appointmentService.getAll();
		ArrayList<AppointmentDTOView> appointmentDTOs = new ArrayList<AppointmentDTOView>();
		
		for(Appointment a : appointments){
			if(a.getUser() != null) {
				if(a.getUser().getEmail().equals(user)) {
					AppointmentDTOView app = new AppointmentDTOView();
					app.setId(a.getId().toString());
					app.setCenterName(a.getCenter().getName());
					app.setDate(a.getDate().toString());
					app.setTime(a.getTime().toString());
					appointmentDTOs.add(app);
				}
			}
		}
		
		return appointmentDTOs;
	}

}
