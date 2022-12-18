package main.dto;
import main.model.*;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

public class AppointmentDTO {
        
    private Center center;
    private LocalDateTime time;
    private User user;

    public AppointmentDTO(Long id, Center center, LocalDateTime time, User user) {
        this.center = center;
        this.time = time;
        this.user = null;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
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
