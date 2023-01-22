package main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.model.Penal;

@Repository
public interface PenalRepository extends JpaRepository<Penal, Long>{
	Penal findByEmail(String email);
}
