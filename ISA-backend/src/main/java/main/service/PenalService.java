package main.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Penal;
import main.repository.PenalRepository;

@Service
public class PenalService {
	@Autowired
	private PenalRepository penalRepository;
	
	public Penal getById(String email){
		return penalRepository.findByEmail(email);
	}
	
	public ArrayList<Penal> getAll(){
		return (ArrayList<Penal>) penalRepository.findAll();
	}
}
