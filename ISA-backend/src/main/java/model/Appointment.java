package model;

import java.time.LocalTime;
import java.util.List;

public class Appointment {
	
	public Long id;
	public List<User> staff;
	public LocalTime time;
	public int duration;
	
	public Appointment(Long id, List<User> staff, LocalTime time, int duration) {
		super();
		this.id = id;
		this.staff = staff;
		this.time = time;
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
