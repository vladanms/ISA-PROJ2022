package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
}
