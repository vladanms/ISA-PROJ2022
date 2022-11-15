package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.AppointmentService;

@RestController
@RequestMapping(value = "api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
}
