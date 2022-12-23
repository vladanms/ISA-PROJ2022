package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Appointment;
import main.model.RestrictedAppointment;

@Repository
public interface RestrictedAppointmentRepository extends JpaRepository<RestrictedAppointment, Long>{
	RestrictedAppointment findByAppointmentId(String id);
}
