package main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.dto.AppointmentDTO;
import main.model.*;
import main.repository.AppointmentRepository;

@Service
@Transactional
public class AppointmentService {
    
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
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

	@Transactional
	public boolean scheduleFreeAppointment(Appointment appointment)
    {
		try {
            Appointment appointmentToSchedule = appointmentRepository.findOneById(appointment.getId());
             if (appointmentToSchedule == null) {
                    return false;
             }
             appointmentRepository.save(appointment);
             return true;

        } catch(PessimisticLockingFailureException ex) {
            throw new PessimisticLockingFailureException("Appointment already scheduled!");
        }
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
    
    public Optional<Appointment> getById(Long id) {
    	return appointmentRepository.findById(id);
    }

}
