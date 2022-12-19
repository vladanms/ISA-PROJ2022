package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.*;

@Repository
public interface UserAdminCenterRepository extends JpaRepository<Admin, Long> {

	UserAdminCenter findByCenter(Center center);
	
    UserRegistered findByUser(User user);
}
