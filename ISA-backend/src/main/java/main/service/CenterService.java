package main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Center;
import main.repository.CenterRepository;

@Service
public class CenterService {

	@Autowired
	private CenterRepository centerRepository;
	
	public void Add(Center center) 
	{
		centerRepository.save(center);
	}
	
	public void Edit(Center center)
	{
		
	}
}
