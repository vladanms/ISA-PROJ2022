package main.dto;

public class FreeAppointmentScheduleDTO {
	private String appointmentId;
	private String email;
	private String centerId;
	
	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public FreeAppointmentScheduleDTO() {
		super();
	}

	/**
	 * @return the appointmentId
	 */
	public String getAppointmentId() {
		return appointmentId;
	}

	/**
	 * @param appointmentId the appointmentId to set
	 */
	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
