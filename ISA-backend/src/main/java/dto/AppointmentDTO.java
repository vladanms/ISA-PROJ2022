package dto;

import java.time.LocalTime;
import java.util.List;

import model.User;

public class AppointmentDTO {

	public List<User> staff;
	public LocalTime time;
	public int duration;
	
	public AppointmentDTO(Long id, List<User> staff, LocalTime time, int duration) {
		super();
		this.staff = staff;
		this.time = time;
		this.duration = duration;
	}


	public List<User> getStaff() {
		return staff;
	}

	public void setStaff(List<User> staff) {
		this.staff = staff;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
}
