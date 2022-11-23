package main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long>{
	
	Center findByName(String name);
	Center findByAddress(String address);
	Center findByAvgGradeGreaterThanEqual(Float grade);
	

}
