package main.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.dto.CenterDTO;
import main.dto.UserDTO;
import main.model.Center;
import main.model.User;
import main.model.UserType;
import main.service.CenterService;
import main.service.UserService;

@RestController
@RequestMapping(value = "center")
public class CenterController {

	@Autowired
	private CenterService centerService;

	
	@PostMapping("/newCenter")
	public ResponseEntity<User> saveCenter(@RequestBody CenterDTO centerDTO) {

		Random id = new Random();
		Center center = new Center(
					id.nextLong(),
					centerDTO.getName(),
					centerDTO.getAddress(),
					centerDTO.getDescription(),
					centerDTO.getAvgGrade()
					//centerDTO.getGrade(),
					//centerDTO.getFreeAppointments(),
					//centerDTO.getAdmins()
				);
		
			return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@PostMapping("/findCenter")
	public ResponseEntity<User> findCenter(@RequestBody CenterDTO centerDTO)
	{
		centerService.FindCenter(centerDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
