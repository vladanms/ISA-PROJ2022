package main.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.RestrictedAppointment;
import main.repository.RestrictedAppointmentRepository;

@Service
public class RestrictedAppointmentService {
	@Autowired
	private RestrictedAppointmentRepository restrictedAppointmentRepository;
	
	public Boolean add(RestrictedAppointment appointment) 
	{
		restrictedAppointmentRepository.save(appointment);
        return true;
	}
	
	public void remove(Long id) {
		restrictedAppointmentRepository.deleteById(id);
	}
	
	public RestrictedAppointment getById(String id){
		return restrictedAppointmentRepository.findByAppointmentId(id);
	}
	
	public ArrayList<RestrictedAppointment> getAll()
    {
    	ArrayList<RestrictedAppointment> appointments = new ArrayList<RestrictedAppointment>();
        for (RestrictedAppointment a: restrictedAppointmentRepository.findAll()) {
        	appointments.add(a);
        }
        return appointments;
    }
}
