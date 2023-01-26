package main.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
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
import main.dto.AttendedAppointmentDTOView;
import main.dto.CenterDTO;
import main.dto.CenterDTOView;
import main.dto.FreeAppointmentScheduleDTO;
import main.model.*;
import main.service.AppointmentService;
import main.service.AttendedAppointmentService;
import main.service.CenterService;
import main.service.RestrictedAppointmentService;
import main.service.UserService;
import main.support.QRCodeGenerator;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "appointment")
public class AppointmentController {

    User loggedInUser = new User();
    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/QRCode";
    
    public boolean generateQRCodeImage(Long idAppointment) throws Exception {
    	Optional<Appointment> a = appointmentService.getById(idAppointment);
    	String content = "Appointment,\n"
	    		+ "Center: " + a.get().getCenter().getName() + "\n"
	            + "Date: " + a.get().getDate().toString() + "\n"
	            + "Time: " + a.get().getTime().toString();
        QRCodeGenerator.generateQRCodeImage(content,250,250,QR_CODE_IMAGE_PATH + idAppointment + ".png");
        return true;
    }
    
    @Autowired
    private CenterService centerService;
    
    @Autowired
	private AppointmentService appointmentService;
    
    @Autowired
	private AttendedAppointmentService attendedAppointmentService;
    
    @Autowired
	private UserService userService;
    
    @Autowired
    private RestrictedAppointmentService restrictedAppointmentService;
    
    @Autowired
    private JavaMailSender mailSender;

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
    public ResponseEntity<Appointment> scheduleFreeAppointment(@RequestBody FreeAppointmentScheduleDTO fasDTO) throws Exception 
    {
    	generateQRCodeImage(Long.parseLong(fasDTO.getAppointmentId()));
    	Optional<Appointment> a = appointmentService.getById(Long.parseLong(fasDTO.getAppointmentId()));
    	if(a.get() == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	a.get().setUser(userService.findByEmail(fasDTO.getEmail()));
    	appointmentService.scheduleFreeAppointment(a.get());
    	
    	String toAddress = fasDTO.getEmail();
	    String fromAddress = "ISA-Email";
	    String senderName = "FTN";
	    String subject = "Free appointment scheduled";
	    String content = "Appointment,<br>"
	    		+ "Center: [[CENTER]]<br>"
	            + "Date: [[DATE]]<br>"
	            + "Time: [[TIME]]<br>"
	            + "<img src=\"[[LINK]]\" width=\"200\" height=\"200\">";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	    
	    content = content.replace("[[CENTER]]", a.get().getCenter().getName());
	    content = content.replace("[[DATE]]", a.get().getDate().toString());
	    content = content.replace("[[TIME]]", a.get().getTime().toString());
	    content = content.replace("[[LINK]]", QR_CODE_IMAGE_PATH + Long.parseLong(fasDTO.getAppointmentId()) + ".png");
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
    	
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
    
    @GetMapping("/getAttendedAppointments")
    public @ResponseBody ArrayList<AttendedAppointmentDTOView> getAttendedAppointments(@Param("user") String user){ 
		ArrayList<AttendedAppointment> appointments = attendedAppointmentService.getAll();
		ArrayList<AttendedAppointmentDTOView> appointmentDTOs = new ArrayList<AttendedAppointmentDTOView>();
		
		for(AttendedAppointment a : appointments){
			if(a.getUser().getEmail().equals(user) && a.getDate().isBefore(LocalDate.now())) {
				AttendedAppointmentDTOView app = new AttendedAppointmentDTOView();
				app.setId(a.getId().toString());
				app.setCenterName(a.getCenter().getName());
				app.setDate(a.getDate().toString());
				app.setTime(a.getTime().toString());
				app.setPrice(a.getPrice());
				app.setLength(a.getLength());
				appointmentDTOs.add(app);
			}
		}
		
		return appointmentDTOs;
	}
    
    @GetMapping("/getAttendedAppointmentsByCenter")
    public @ResponseBody ArrayList<AttendedAppointmentDTOView> getAttendedAppointmentsByCenter(@Param("user") String user){ 
		ArrayList<AttendedAppointment> appointments = attendedAppointmentService.getAll();
		ArrayList<AttendedAppointmentDTOView> appointmentDTOs = new ArrayList<AttendedAppointmentDTOView>();
		
		for(AttendedAppointment a : appointments){
			if(a.getUser().getEmail().equals(user) && a.getDate().isBefore(LocalDate.now())) {
				AttendedAppointmentDTOView app = new AttendedAppointmentDTOView();
				app.setId(a.getId().toString());
				app.setCenterName(a.getCenter().getName());
				app.setDate(a.getDate().toString());
				app.setTime(a.getTime().toString());
				app.setPrice(a.getPrice());
				app.setLength(a.getLength());
				appointmentDTOs.add(app);
			}
		}
		Collections.sort(appointmentDTOs, Comparator.comparing(AttendedAppointmentDTOView::getCenterName));
		return appointmentDTOs;
	}
    @GetMapping("/getAttendedAppointmentsByPrice")
    public @ResponseBody ArrayList<AttendedAppointmentDTOView> getAttendedAppointmentsByPrice(@Param("user") String user){ 
		ArrayList<AttendedAppointment> appointments = attendedAppointmentService.getAll();
		ArrayList<AttendedAppointmentDTOView> appointmentDTOs = new ArrayList<AttendedAppointmentDTOView>();
		
		for(AttendedAppointment a : appointments){
			if(a.getUser().getEmail().equals(user) && a.getDate().isBefore(LocalDate.now())) {
				AttendedAppointmentDTOView app = new AttendedAppointmentDTOView();
				app.setId(a.getId().toString());
				app.setCenterName(a.getCenter().getName());
				app.setDate(a.getDate().toString());
				app.setTime(a.getTime().toString());
				app.setPrice(a.getPrice());
				app.setLength(a.getLength());
				appointmentDTOs.add(app);
			}
		}
		Collections.sort(appointmentDTOs, Comparator.comparing(AttendedAppointmentDTOView::getPrice));
		return appointmentDTOs;
	}
    @GetMapping("/getAttendedAppointmentsByLength")
    public @ResponseBody ArrayList<AttendedAppointmentDTOView> getAttendedAppointmentsByLength(@Param("user") String user){ 
		ArrayList<AttendedAppointment> appointments = attendedAppointmentService.getAll();
		ArrayList<AttendedAppointmentDTOView> appointmentDTOs = new ArrayList<AttendedAppointmentDTOView>();
		
		for(AttendedAppointment a : appointments){
			if(a.getUser().getEmail().equals(user) && a.getDate().isBefore(LocalDate.now())) {
				AttendedAppointmentDTOView app = new AttendedAppointmentDTOView();
				app.setId(a.getId().toString());
				app.setCenterName(a.getCenter().getName());
				app.setDate(a.getDate().toString());
				app.setTime(a.getTime().toString());
				app.setPrice(a.getPrice());
				app.setLength(a.getLength());
				appointmentDTOs.add(app);
			}
		}
		Collections.sort(appointmentDTOs, Comparator.comparing(AttendedAppointmentDTOView::getLength));
		return appointmentDTOs;
	}
    
    @GetMapping("/getCenters")
    public @ResponseBody ArrayList<Center> getCenters(@Param("date") LocalDate date, @Param("time") LocalTime time)
    {
		ArrayList<Appointment> appointments = appointmentService.getAll();
		ArrayList<Center> centers = new ArrayList<Center>();
    	for(Appointment a: appointments)
    	{
    		if(a.getDate() == date & a.getTime() == time)
    		{
    			for(Center c: centerService.findAll())
    			{
    				centers.add(a.getCenter());
    			}
    		}
    	}
		return centers;
    }
    
}
