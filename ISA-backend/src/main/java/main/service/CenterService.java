package main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.dto.CenterDTO;
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
	
	public ArrayList<Center> findAll(){
        ArrayList<Center> centers = new ArrayList<Center>();
        for (Center c: centerRepository.findAll()) {
        	centers.add(c);
        }
        return centers;
    }
	
	public List<Center> FindCenter(CenterDTO centerDTO)
	{
		List<Center> centers = centerRepository.findByCustomParameters(centerDTO.getName(), centerDTO.getAddress(), centerDTO.getAvgGrade());
		
		return centers;
	}
}
