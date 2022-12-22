package main.dto;
import main.model.*;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentDTO {
        
    private Center center;
    private LocalDate date;
    private LocalTime time;
    private User user;

    public AppointmentDTO(Long id, Center center, LocalDate date, LocalTime time, User user) {
        this.center = center;
        this.date = date;
        this.time = time;
        this.user = null;
    }

	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
		this.time = time;
	}


	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    

}
