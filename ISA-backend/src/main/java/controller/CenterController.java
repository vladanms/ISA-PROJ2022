package controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.CenterDTO;
import dto.UserDTO;
import model.Center;
import model.User;
import model.UserType;
import service.CenterService;
import service.UserService;

@RestController
@RequestMapping(value = "api/centers")
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
					centerDTO.getGrade(),
					centerDTO.getFreeAppointments(),
					centerDTO.getAdmins()
				);
		
			return new ResponseEntity<>(HttpStatus.OK);

	}

}
