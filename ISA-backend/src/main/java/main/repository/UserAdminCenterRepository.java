package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.*;

@Repository
public interface UserAdminCenterRepository extends JpaRepository<UserAdminCenter, Long> {

	UserAdminCenter findByCenter(Center center);
	
    UserAdminCenter findByUser(User user);
}
