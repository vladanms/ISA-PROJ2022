package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long>{
	
	Center findByName(String name);
	Center findByAdress(String address);
	Center findByAvgGradeGreaterThanEqual(Float grade);
	

}
