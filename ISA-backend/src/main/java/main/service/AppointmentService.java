package main.service;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dto.AppointmentDTO;
import main.model.*;
import main.repository.AppointmentRepository;

@Service
public class AppointmentService {
    
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public Boolean AddAppointment(Appointment appointment) 
	{
		if(Duration.between(appointment.getTime(), LocalDateTime.now()).toHours() < 24)
        {
            return false;
        }
        appointmentRepository.save(appointment);
        return true;
	}


    public int schedule(Appointment appointment, User user)
    {
        if(appointment.getUser() != null)
        {
            return 1;
            //zauzet termin
        }

		if(Duration.between(appointment.getTime(), LocalDateTime.now()).toHours() < 24)
        {
            return 2; 
            //24 sata minimum
        }
        if(user.getPersonalFile() == null)
        {
        	return 3;
        	//mora imati popunjen upitnik
        }
        List<Appointment> userAppointments = appointmentRepository.findByUser(user);
        for(int i = 0; i < userAppointments.size(); i++)
        {
            if(Duration.between(LocalDateTime.now(), userAppointments.get(i).getTime()).toDays() < 180)
            {
                return 4;
                //mora proci 6 meseci od prethodnog davanja
            }
        }


        appointment.setUser(user);
        appointmentRepository.save(appointment);
        return 0;
    }

    public List<Appointment> getAllByTime(LocalDateTime time)
    {
        return appointmentRepository.findByTime(time);
    }

    public List<Appointment> getAllByCenter(Center center)
    {
        return appointmentRepository.findByCenter(center);
    }

    public boolean cancel(Appointment appointment)
    {
        if(Duration.between(appointment.getTime(), LocalDateTime.now()).toHours() < 24)
        {
            return false; 
            //24 sata minimum
        }
        appointment.setUser(null);
        return true;
    }
    
    public ArrayList<Appointment> getAll()
    {
    	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        for (Appointment a: appointmentRepository.findAll()) {
        	appointments.add(a);
        }
        return appointments;
    }

}
