package main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import main.model.Center;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long>{
	
	Center findByName(String name);
	Center findByAddress(String address);
	List<Center> findByAvgGradeGreaterThanEqual(Float grade);
	
	@Query("Select C FROM Center C WHERE (:name is null or C.name = :name) and (:address is null or C.address = :address) and (:avgGrade is null or C.avgGrade < :avgGrade)")
	List<Center> findByCustomParameters(@Param("name") String name, @Param("address") String address, @Param("avgGrade") Float avgGrade);
	

}
