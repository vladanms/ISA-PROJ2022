package main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import main.dto.CenterDTO;
import main.dto.CenterDTOView;
import main.dto.UserDTO;
import main.model.Center;
import main.model.User;
import main.model.UserType;
import main.service.CenterService;
import main.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "center")
public class CenterController {

	@Autowired
	private CenterService centerService;

	
	@PostMapping("/newCenter")
	public ResponseEntity<Center> saveCenter(@RequestBody CenterDTO centerDTO) {

		Random id = new Random();
		Center center = new Center(
					id.nextLong(),
					centerDTO.getName(),
					centerDTO.getAddress(),
					centerDTO.getDescription(),
					centerDTO.getAppointments(),
					centerDTO.getAvgGrade()
					//centerDTO.getGrade(),
					//centerDTO.getFreeAppointments(),
					//centerDTO.getAdmins()
				);
		
			centerService.Add(center);
			return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@GetMapping("/getAll")
    public @ResponseBody ArrayList<Center> getAll(){ return centerService.findAll(); }
	
	@GetMapping("/getAllDTO")
    public @ResponseBody ArrayList<CenterDTOView> getAllDTO(){ 
		ArrayList<Center> centers = centerService.findAll();
		ArrayList<CenterDTOView> centerDTOs = new ArrayList<CenterDTOView>();
		
		for(Center c : centers){
			CenterDTOView cen = new CenterDTOView();
			cen.setId(c.getId().intValue());
			cen.setName(c.getName());
			cen.setAddress(c.getAddress());
			cen.setDescription(c.getDescription());
			cen.setAvgGrade(c.getAvgGrade());
			centerDTOs.add(cen);
		}
		
		return centerDTOs;
	}
	
	@GetMapping("/findCenter")
	public ResponseEntity<ArrayList<Center>> findCenter(@RequestBody CenterDTO centerDTO)
	{
		ArrayList<Center> centers = centerService.FindCenter(centerDTO);
		return new ResponseEntity<>(centers, HttpStatus.OK);
	}

}
