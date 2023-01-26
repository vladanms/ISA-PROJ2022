package main.repository;
import main.model.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

    List<Appointment> findByTime(LocalDateTime time); 
    List<Appointment> findByTimeAndUser(LocalDateTime time, User user); 
    List<Appointment> findByUser(User user); 
    List<Appointment> findByCenter(Center center);
    Optional<Appointment> findById(Long id);
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select a from Appointment a where a.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Appointment findOneById(@Param("id")Long id);
}
