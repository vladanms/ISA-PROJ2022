package main.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.AttendedAppointment;
import main.repository.AttendedAppointmentRepository;

@Service
public class AttendedAppointmentService {
	
	@Autowired
	private AttendedAppointmentRepository attendedAppointmentRepository;
	
	public Optional<AttendedAppointment> getById(Long id) {
    	return attendedAppointmentRepository.findById(id);
    }
	
	 public ArrayList<AttendedAppointment> getAll()
	    {
	    	ArrayList<AttendedAppointment> appointments = new ArrayList<AttendedAppointment>();
	        for (AttendedAppointment a: attendedAppointmentRepository.findAll()) {
	        	appointments.add(a);
	        }
	        return appointments;
	    }
}
