package main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.AttendedAppointment;
import main.model.User;

@Repository
public interface AttendedAppointmentRepository extends JpaRepository<AttendedAppointment, Long>{
	List<AttendedAppointment> findByUser(User user); 
	Optional<AttendedAppointment> findById(Long id);
}
